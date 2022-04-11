package main;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class Fondo implements GLEventListener, KeyListener {

	double tan60 = Math.tan(Math.PI / 3.);
	private GL2 gl;
	private GL2 fondo;
	private GLU glu;
	private GLUT glut;
	private float[] blanco = { 1, 1, 1, 0 };
	private float[] ninguno = { 0, 0, 0, 1 };
	private float[] posicion = { 0, 1, 1, 0 };
	private static float mov_x = 0;
	private static float paso_rotacion = 3f;
	private static float angle_x = 0;
	private static float angle_y = 0;
	private static float angle_z = 0;

	public Fondo() {
		// TODO Auto-generated method stub
		Frame frame = new Frame("Fondo");
		GLCanvas miCanvas = new GLCanvas();
		miCanvas.addGLEventListener(new Fondo());
		miCanvas.addKeyListener(new Fondo());
		frame.add(miCanvas);
		FPSAnimator animator = new FPSAnimator(miCanvas, 10);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				animator.stop();
				System.exit(0);
			}
		});
		frame.setSize(800, 480);
		frame.setVisible(true);
		animator.start();
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(1f, 1f, 1f);
		gl.glTranslatef(mov_x, 0, 0);
		gl.glRotatef(angle_x, 1, 0, 0);
		gl.glRotatef(angle_y, 0, 0, 1);
		gl.glRotatef(angle_z, 0, 1, 0);
		square();
		gl.glPopMatrix();
		gl.glFlush();
	}

	private void square() {
		// TODO Auto-generated method stub
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 0, 0);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(1, 0, 0);
		gl.glVertex3d(1, 1, 0);
		gl.glVertex3d(0, 1, 0);

		gl.glColor3d(0, 1, 0);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(0, 0, 1);
		gl.glVertex3d(1, 0, 1);
		gl.glVertex3d(1, 0, 0);

		gl.glColor3d(0, 0, 1);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(0, 1, 0);
		gl.glVertex3d(0, 1, 1);
		gl.glVertex3d(0, 0, 1);

		gl.glColor3d(0, 1, 1);
		gl.glVertex3d(1, 0, 0);
		gl.glVertex3d(1, 0, 1);
		gl.glVertex3d(1, 1, 1);
		gl.glVertex3d(1, 1, 0);

		gl.glColor3d(1, 0, 1);
		gl.glVertex3d(0, 0, 1);
		gl.glVertex3d(1, 0, 1);
		gl.glVertex3d(1, 1, 1);
		gl.glVertex3d(0, 1, 1);

		gl.glColor3d(1, 1, 0);
		gl.glVertex3d(0, 1, 0);
		gl.glVertex3d(1, 1, 0);
		gl.glVertex3d(1, 1, 1);
		gl.glVertex3d(0, 1, 1);

		gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		gl = drawable.getGL().getGL2();
		glut = new GLUT();
		gl.glClearColor(1, 1, 1, 0);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(-20, 20, -20, 20, -10, 10);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ninguno, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, blanco, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, posicion, 0);
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
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
