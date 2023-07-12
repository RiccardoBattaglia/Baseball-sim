package it.polito.tdp.baseball;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.jgrapht.alg.util.Pair;

import it.polito.tdp.baseball.model.Model;
import it.polito.tdp.baseball.model.People;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConnesse;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnDreamTeam;

    @FXML
    private Button btnGradoMassimo;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtYear;

    
    
    @FXML
    void doCalcolaConnesse(ActionEvent event) {
    
    	this.txtResult.appendText("Ci sono " + this.model.getComponente().size() + " componenti connesse.\n\n");
    	
    }

    
    
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	

   	 String a = txtYear.getText() ;
    	
    	if(a.equals("")) {
    		txtResult.setText("Inserire un anno.\n");
    		return ;
    	}
    	
    	int anno = 0 ;

    	try {
	    	anno = Integer.parseInt(a) ;
    	} catch(NumberFormatException e) {
    		txtResult.setText("Inserire un numero come anno.\n");
    		return ;
    	}
    	
    	if(!this.model.getAnni().contains(anno)) {
    		txtResult.setText("Inserire un anno valido.\n");
    		return ;
    	}
    	
       String s = txtSalary.getText() ;
    	
    	if(s.equals("")) {
    		txtResult.setText("Inserire un salario.\n");
    		return ;
    	}
    	
    	double salario;
    	
    	try {
	    	salario = Double.parseDouble(s) ;
    	} catch(NumberFormatException e) {
    		txtResult.setText("Inserire un numero come salario.\n");
    		return ;
    	}
    	
//   	creazione grafo
   	this.model.creaGrafo(anno, salario*1000000);
   	
   	
//   	stampa grafo
   	this.txtResult.setText("Grafo creato.\n");
   	this.txtResult.appendText("Ci sono " + this.model.nVertici() + " vertici\n");
   	this.txtResult.appendText("Ci sono " + this.model.nArchi() + " archi\n\n");
   	
   	btnConnesse.setDisable(false);
   	btnGradoMassimo.setDisable(false);
   	
//   	Set<User> vertici = this.model.getVertici();
//   	
//   	for(User i : vertici) {
//   		cmbUtente.getItems().add(new Pair<String, String>(i.getName(), i.getUserId()));
//   	}
//   	
    	
    }

    
    @FXML
    void doDreamTeam(ActionEvent event) {

    }

    
    @FXML
    void doGradoMassimo(ActionEvent event) {
    	
    	int max=0;

    	for(People p : this.model.getVertici()) {
    		if(max<this.model.nArchiPerVertice(p)) {
    			max=this.model.nArchiPerVertice(p);
    		}
    	}
    	
    	for(People p : this.model.getVertici()) {
    		if(max==this.model.nArchiPerVertice(p)) {
    			this.txtResult.appendText("Nodo di grado massimo:\nGrado:" + max + " <-> Vertice: " + p + " \n\n");
    		}
    	}
    	
    }

    
    @FXML
    void initialize() {
        assert btnConnesse != null : "fx:id=\"btnConnesse\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDreamTeam != null : "fx:id=\"btnDreamTeam\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGradoMassimo != null : "fx:id=\"btnGradoMassimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSalary != null : "fx:id=\"txtSalary\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYear != null : "fx:id=\"txtYear\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }

}
