package com.ingeint.process;

import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.eevolution.model.MHRMovement;
import org.eevolution.model.MHRProcess;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.ingeint.base.CustomProcess;

public class CreateXML extends CustomProcess {

	int p_HR_Concept_ID = 0;
	Timestamp ValidFrom = null;
	Timestamp ValidTo = null;
	Timestamp DateDoc = null;
    Integer p_AD_Org_ID = 0;
	
	protected void prepare() {
		for (ProcessInfoParameter para : getParameter()) {
			String name = para.getParameterName();
			if (name.equals(MOrg.COLUMNNAME_AD_Org_ID))
				p_AD_Org_ID = para.getParameterAsInt();
			else if (name.equals("DateDoc"))
				DateDoc = para.getParameterAsTimestamp();
			else if (name.equals("HR_Concept_ID"))
				p_HR_Concept_ID = para.getParameterAsInt();
			else if (name.equals("ValidFrom"))
				ValidFrom = para.getParameterAsTimestamp();
			else if ( name.equals("ValidTo"))
				ValidTo = para.getParameterAsTimestamp();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		MOrg org = new MOrg(getCtx(), p_AD_Org_ID, get_TrxName());
		MOrgInfo orgInfo = MOrgInfo.get(p_AD_Org_ID);
		String period = DateDoc.toString().substring(0, 8).replace("-","");
		// Root Element
		Element root = new Element("RelacionRetencionesISLR");
		root.setAttribute("RifAgente", orgInfo.getTaxID());
		root.setAttribute("Periodo", period);
		
		List<MHRMovement> movements = new Query(getCtx(), MHRMovement.Table_Name,
				"ValidFrom >= ? And ValidTo <= ? AND HR_Concept_ID = ? AND AD_Client_ID = ? AND AD_Org_ID = ? ", get_TrxName())
						.setParameters(new Object[] { ValidFrom, ValidTo, p_HR_Concept_ID, getAD_Client_ID(), p_AD_Org_ID })
						.list();

		for (MHRMovement move : movements) {

			// Element 1
			Element detail = new Element("DetalleRetencion");
			detail.addContent(new Element("RifRetenido").addContent("V" + move.getC_BPartner().getTaxID()));
			detail.addContent(new Element("NumeroFactura").addContent("0"));
			detail.addContent(new Element("NumeroControl").addContent("NA"));
			detail.addContent(new Element("FechaOperacion").addContent(move.getHR_Process().getDateAcct().toString().substring(0, 10).replace("-","")));
			detail.addContent(new Element("CodigoConcepto").addContent("001"));
			detail.addContent(new Element("MontoOperacion").addContent(move.getAmount().toString()));
			detail.addContent(new Element("PorcentajeRetencion").addContent("0"));

			root.addContent(detail);

		}
		
		Document doc = new Document();
		doc.setRootElement(root);

		// Create the XML
		XMLOutputter outter = new XMLOutputter();
		Format format = Format.getPrettyFormat();
	    format.setEncoding("ISO-8859-1");
		outter.setFormat(format);		
		File file = new File(org.getName() + "_" + period + ".xml");
		outter.output(doc, new FileWriter(file));

		processUI.download(file);
		return "Creado: " + file;
	}

}
