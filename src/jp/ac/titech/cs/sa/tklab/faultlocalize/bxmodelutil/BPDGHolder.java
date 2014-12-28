package jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.BPDG;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Node;

/**
 * 
 * @author ?
 *
 */
public class BPDGHolder {
	static final String BXMODEL_PACKAGE_NAME = "jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel";

	private List<Node> nodes;
	private int idx;

	public BPDGHolder(File file) throws JAXBException{
		BPDG bpdg = (BPDG) JAXBContext.newInstance(BXMODEL_PACKAGE_NAME)
				.createUnmarshaller().unmarshal(file);
		nodes = bpdg.getNodes().getNodes();
		idx = 0;
	}

	public Node getNextNode(){
	    if(idx >= nodes.size()) return null;
		return nodes.get(idx++);
	}
}
