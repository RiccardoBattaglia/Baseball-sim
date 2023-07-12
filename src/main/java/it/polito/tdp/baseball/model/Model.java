package it.polito.tdp.baseball.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.baseball.db.BaseballDAO;


public class Model {
	

	private BaseballDAO dao;
	private Graph<People, DefaultEdge> grafo;
	private Map<String, People> peopleMap;
	private List<People> peopleList;
	
	public Model() {
		this.dao = new BaseballDAO();
		
		this.peopleMap=new HashMap<>();
		this.peopleList = this.dao.readAllPlayers();
		for(People i : peopleList) {
			this.peopleMap.put(i.getPlayerID(), i);
		}
		
	}
	

	public void creaGrafo(Integer anno, Double salario) {
		// TODO Auto-generated method stub

	this.grafo = new SimpleWeightedGraph<People, DefaultEdge>(DefaultEdge.class);
		
	// Aggiunta VERTICI 
	List<People> vertici=new LinkedList<>();
	
	vertici.addAll(this.dao.getPlayerPerAS(anno, salario));
	
	Graphs.addAllVertices(this.grafo, vertici);

	
	// Aggiunta ARCHI

	for (People v1 : vertici) {
		for (People v2 : vertici) {
			if(this.dao.trovaCompagni(anno, v1.getPlayerID()).contains(v2) && !v1.getPlayerID().equals(v2.getPlayerID())){ 
		      this.grafo.addEdge(v1,v2);
			}
			}
			}

	}

public int nVertici() {
	return this.grafo.vertexSet().size();
}

public int nArchi() {
	return this.grafo.edgeSet().size();
}

public Set<People> getVertici(){
	
	Set<People> vertici=this.grafo.vertexSet();
	
	return vertici;
}

public Set<DefaultEdge> getArchi(){
	
	Set<DefaultEdge> vertici=this.grafo.edgeSet();
	
	return vertici;
}

public List<Set<People>> getComponente() {
	ConnectivityInspector<People, DefaultEdge> ci = new ConnectivityInspector<>(this.grafo) ;
	return ci.connectedSets() ;
}

public int nArchiPerVertice(People p) {
	

	return this.grafo.edgesOf(p).size();
}

//public List<Pair<String, Double>> trovaSimili(User v1){
//
//	List<Pair<String, Double>> ris = new LinkedList<>();
//
//	double p=0;
//	
//	for(DefaultWeightedEdge i :this.grafo.outgoingEdgesOf(v1)) {
//		if(this.grafo.getEdgeWeight(i)>=p) {
//			p=this.grafo.getEdgeWeight(i);
//		}
//	}
//		
//		for(DefaultWeightedEdge i :this.grafo.outgoingEdgesOf(v1)) {
//			if(this.grafo.getEdgeWeight(i)==p) {
//			ris.add(new Pair<>(this.grafo.getEdgeSource(i).getUserId(), this.grafo.getEdgeWeight(i)));
////			System.out.println(this.grafo.getEdgeSource(i).getUserId()+"--"+ this.grafo.getEdgeWeight(i));
//		}
//		}
//		return ris;
//}

public List<Integer> getAnni(){
	return this.dao.getAnni();
}
	
	
	
}