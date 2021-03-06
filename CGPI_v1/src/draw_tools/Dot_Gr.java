package draw_tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a graphic point
 * @author Bruno/Gabriel/Guilherme/Felipe
 */
public class Dot_Gr extends Draw_Dot {
	Color cor; // cor do ponto
	String nomeP; // nome do ponto
	Color corNomeP; // cor do nome  
	int diametro=1; // diametro do ponto, default = 1

	/**
	 * Constroi um ponto na posicao x, y e com os atributos
	 * 
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param cor cor do ponto a ser construido
	 * @param diametro diametro do ponto
	 */
	public Dot_Gr(int x, int y, Color cor){
		super((double)x, (double)y);
		setCor(cor);	 
		setCorNomeP(Color.BLACK);	 
		setNomeP("");	 
	}

	/**
	 * Constroi um ponto na posicao x, y e com os atributos
	 * 
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param cor cor do ponto a ser construido
	 * @param diametro diametro do ponto
	 */
	public Dot_Gr(int x, int y, Color cor, int diametro){
		super((double)x, (double)y);
		setCor(cor);	 
		setCorNomeP(Color.BLACK);	 
		setNomeP("");	 
		setDiametro(diametro);
	}

	/**
	 * Constroi um ponto na posicao x, y e com os atributos
	 * 
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param cor cor do ponto a ser construido
	 * @param nomeP nome do ponto
	 * @param diametro diametro do ponto
	 */
	public Dot_Gr(int x, int y, Color cor, String nomeP, int diametro){
		super((double)x, (double)y);
		setCor(cor);	 
		setCorNomeP(Color.BLACK);	 
		setNomeP(nomeP);
		setDiametro(diametro);
	}
	/**
	 * Constroi um ponto na posicao x, y e com os atributos
	 * 
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param cor cor do ponto a ser construido
	 * @param nomeP nome do ponto
	 * @param diametro diametro do ponto
	 */
	public Dot_Gr(int x, int y, Color cor, String nomeP){
		super((double)x, (double)y);
		setCor(cor);	 
		setCorNomeP(Color.BLACK);	 
		setNomeP(nomeP);
	}
	/**
	 * Constroi um ponto baseado em outro ponto grafico
	 * 
	 * @param pg outro ponto
	 * @param cor cor do ponto a ser construido
	 * @param diametro diametro do ponto
	 */
	public Dot_Gr(Dot_Gr pg, Color cor, int diametro){
		super(pg.getX(), pg.getY());	 
		setCor(cor);	 
		setCorNomeP(Color.BLACK); 	 
		setNomeP("");	 
		setDiametro(diametro);
	}

	/**
	 * Constroi um ponto na posicao 0, 0 com diametro e cor Black
	 * 
	 * @param diametro
	 */
	public Dot_Gr(int x, int y){
		super((double)x, (double)y);
		setCor(Color.BLACK);	 
		setCorNomeP(Color.BLACK);	 
		setNomeP("");	 
	}

	/**
	 * Constroi um ponto na posicao 0, 0 com diametro e cor Black
	 * 
	 * @param diametro
	 */
	public Dot_Gr(int diametro){
		super(0, 0);	 
		setCor(Color.BLACK);	 
		setCorNomeP(Color.BLACK);	 
		setNomeP("");	 
		setDiametro(diametro);

	}

	private Color getCor() {
		return cor;
	}

	private String getStr() {
		return nomeP;
	}

	private Color getCorStr() {
		return corNomeP;
	}

	private int getDiametro() {
		return diametro;
	}

	private void setDiametro(int diametro) {
		this.diametro = diametro;
	}

	private void setCor(Color cor){
		this.cor = cor;
	}
	private void setCorNomeP(Color corNomeP){
		this.corNomeP = corNomeP;
	}
	private void setNomeP(String nomeP){
		this.nomeP = nomeP;
	}

	/**
	 * desenha um ponto utilizando o oval 
	 * 
	 * @param g contexto grafico
	 */
	public void desenharPonto(GraphicsContext g) {
		// desenha ponto como um oval
		g.setFill(getCor());
		g.fillOval((int)getX() -(getDiametro()/2), (int)getY() - (getDiametro()/2), getDiametro(), getDiametro());

		// desenha nome do ponto
		g.setFill(getCorStr());
		g.strokeText(getStr(), (int)getX() + getDiametro(), (int)getY());
	}
}
