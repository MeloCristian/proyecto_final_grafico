package main;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class Ejemplo2 implements GLEventListener, KeyListener{

	private static GL2 gl;
	private static GLU glu;
	private static GLUT glut;
	private static float[] blanco= {1,1,1,0};
	private static float[] ninguno= {0,0,0,1};
	private static float[] posicion= {0,1,1,0};
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frame=new Frame("Ejemplo Iluminacion 2");		
		GLCanvas miCanvas=new GLCanvas();
		miCanvas.addGLEventListener(new Ejemplo2());
		miCanvas.addKeyListener(new Ejemplo2());
		frame.add(miCanvas);
		FPSAnimator animator=new FPSAnimator(miCanvas,10);
		
		frame.addWindowListener(new WindowAdapter() {			
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				animator.stop();
				System.exit(0);				
			}
		});				
		frame.setSize(640, 480);
		frame.setVisible(true);
		animator.start();

	}		
	

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		gl=drawable.getGL().getGL2();
		gl.glClearColor(1, 1, 1, 0);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		tetera();
		
		gl.glFlush();
		
	}

	private void tetera() {
		// TODO Auto-generated method stub
		float[] ambiente= {1,0,0,1};
		float[] difusa= {0,0,1,1};
		float[] especular= {0,0,1,1};
		gl.glPushMatrix();
		gl.glTranslated(10, 10, 0);
		gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_AMBIENT,ambiente,0);
		gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_DIFFUSE,difusa,0);		
		gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_SPECULAR,especular,0);
		gl.glMaterialf(GL2.GL_FRONT,GL2.GL_SHININESS,80);
		glut.glutSolidTeapot(5);
		gl.glPopMatrix();
		
	}


	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		gl=drawable.getGL().getGL2();
		glut=new GLUT();
		gl.glClearColor(1, 1, 1, 0);	
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0, 20, 0, 20, -10,10);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();	
		gl.glLightfv(GL2.GL_LIGHT0,GL2.GL_AMBIENT,ninguno,0);
		gl.glLightfv(GL2.GL_LIGHT0,GL2.GL_DIFFUSE,blanco,0);
		gl.glLightfv(GL2.GL_LIGHT0,GL2.GL_POSITION,posicion,0);			
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);		
		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==39) {
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}