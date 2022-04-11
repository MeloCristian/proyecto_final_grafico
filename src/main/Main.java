package main;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Main implements GLEventListener, KeyListener {

	double tan60 = Math.tan(Math.PI / 3.);
	private GL2 gl;
	private GLU glu;
	private GLUT glut;
	private float[] blanco = { 1, 1, 1, 0 };
	private float[] ninguno = { 0, 0, 0, 0};
	float SHINE_ALL_DIRECTIONS = 1;
	float[] lightPos = { 0, 50,20, 0};
	private static float mov_x = 0;
	private static float paso_rotacion = 3f;
	private static float angle_x = 0;
	private static float angle_y = 0;
	private static float angle_z = 0;
	private static GLCanvas miCanvas;
	private int texture;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frame = new Frame("Main");
		miCanvas = new GLCanvas();
		miCanvas.addGLEventListener(new Main());
		miCanvas.addKeyListener(new Main());
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
		gl=drawable.getGL().getGL2();
		gl.glClearColor(1, 1, 1, 0);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		fondo();
		setCamera();
		sphere(60,60,-40);
		tetera();
		gl.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		gl = drawable.getGL().getGL2();
		glu = new GLU();
		glut = new GLUT();
		gl.glClearColor(1, 1, 1, 0);
		gl.glEnable(GL2.GL_TEXTURE_2D);
		setCamera();
		setLight();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
	}

	private void sphere(float x, float y, float z) {
		try {
			float[] ambiente= {0.2f,0.2f,0.2f,1f};
			float[] difusa= {1,0.5f,1,1f};
			float[] especular= {0.1f,0.1f,0.1f,1};
			
			URL url = getClass().getResource("/img/aluminio.jpg");
			File file = new File(url.getPath());
			Texture texture = TextureIO.newTexture(file, true);
			this.texture = texture.getTextureObject(gl);
			
			gl.glPushMatrix();		
			gl.glRotatef(0, 1, 0, 0);
			gl.glRotatef(90, 0, 0, 1);
			gl.glRotatef(90, 0, 1, 0);
			gl.glTranslatef(x, y, z);
			gl.glLightfv(GL2.GL_FRONT, GL2.GL_AMBIENT, ambiente, 0);
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, difusa, 0);
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR,especular, 0);
			//gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 0.5f);
			glut.glutSolidCone(20, 30, 10, 10);
			gl.glPopMatrix();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void tetera() {
		
		try {

		      
			float[] ambiente= {0.5f,0.5f,0f,1};
			float[] difusa= {1,0.5f,1,0};
			float[] especular= {0.5f,0.5f,1,0};
			URL url = getClass().getResource("/img/porcelana.jpeg");
			File file = new File(url.getPath());
			Texture texture = TextureIO.newTexture(file, true);
			this.texture = texture.getTextureObject(gl);
			
			gl.glPushMatrix();
			gl.glRotatef(angle_x, 1, 0, 0);
			gl.glRotatef(angle_y, 0, 0, 1);
			gl.glRotatef(angle_z, 0, 1, 0);
			gl.glTranslated(0, 0, 0);
			//gl.glLightfv(GL2.GL_FRONT,GL2.GL_AMBIENT,ambiente,0);
			//gl.glLightfv(GL2.GL_FRONT,GL2.GL_DIFFUSE,ambiente,0);		
			gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_SPECULAR,ambiente,0);
			//gl.glMaterialf(GL2.GL_FRONT,GL2.GL_SHININESS,0.5f);
			glut.glutSolidTeapot(15);
			gl.glPopMatrix();
		} catch (Exception e) {
			System.exit(0);
		}
		// TODO Auto-generated method stub
		
		
	}

	private void setCamera() {
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		float withHeigthRatio = (float) miCanvas.getWidth() / (float) miCanvas.getHeight();
		
		glu.gluPerspective(45, withHeigthRatio, 1, 1000);
		glu.gluLookAt(0,mov_x, 100, 0, 0, 0, 0, 1, 0);

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	private void setLight() {
		gl.glLightfv(GL2.GL_LIGHT0,GL2.GL_AMBIENT,ninguno,0);
		gl.glLightfv(GL2.GL_LIGHT0,GL2.GL_DIFFUSE,blanco,0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPos, 0);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);		
		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(angle_x + " " + angle_y + " " + angle_z);
		// Derecha 39
		if (e.getKeyCode() == 39) {
			mov_x += paso_rotacion;
		}

		// izquierda 37
		if (e.getKeyCode() == 37) {
			mov_x -= paso_rotacion;
		}

		// rotar X- W 87
		if (e.getKeyCode() == 87) {
			angle_x -= paso_rotacion;

		}
		// rotar X+ S 83
		if (e.getKeyCode() == 83) {
			angle_x += paso_rotacion;
		}

		// rotar Y- D 68
		if (e.getKeyCode() == 68) {
			angle_y -= paso_rotacion;
		}

		// rotar Y+ A 65
		if (e.getKeyCode() == 65) {
			angle_y += paso_rotacion;
		}

		// rotar Z+ E 69
		if (e.getKeyCode() == 69) {
			angle_z += paso_rotacion;
		}

		// rotar Z- Q 81
		if (e.getKeyCode() == 81) {
			angle_z -= paso_rotacion;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void fondo() {

		try {
			URL url = getClass().getResource("/img/piso.jpg");
			File file = new File(url.getPath());
			Texture texture_piso = TextureIO.newTexture(file, true);
			this.texture = texture_piso.getTextureObject(gl);
			
			url = getClass().getResource("/img/pared.jpg");
			file = new File(url.getPath());
			Texture texture_pared= TextureIO.newTexture(file, true);
			
			url = getClass().getResource("/img/pared.jpeg");
			file = new File(url.getPath());
			Texture texture_pared2= TextureIO.newTexture(file, true);
			
			float[] ambiente= {1f,1f,1f,1f};
			float[] difusa= {1,1,1,1f};
			float[] especular= {0,0,1,1};
			gl.glPushMatrix();
			gl.glLoadIdentity();
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, ambiente, 1);
		    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, blanco, 0);
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, difusa, 0);
			gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 0.5f);
			gl.glBindTexture(GL2.GL_TEXTURE_2D, this.texture);
			
			int distancia = 80;
			
			gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-distancia, -40, -distancia);
	        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(distancia, -40, -distancia);
	        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(distancia, -40, 20);
	        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-distancia, -40, 20);
	        gl.glEnd();
	        
	        float[] rgba2 = { 1f, 0.6f, 0.6f,0 };
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, rgba2, 0);
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, rgba2, 0);
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, rgba2, 0);
			
			this.texture = texture_pared.getTextureObject(gl); 
	        gl.glBindTexture(GL2.GL_TEXTURE_2D, this.texture);
	        gl.glBegin(GL2.GL_QUADS);
	        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-distancia, -40, -distancia);
	        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(distancia, -40, -distancia);
	        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(distancia, distancia, -distancia);
	        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-distancia, distancia, -distancia); 
	        gl.glEnd();
	        
	        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, blanco, 0);
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, blanco, 0);
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, blanco, 0);
	        gl.glBegin(GL2.GL_QUADS);
	        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(distancia, -40, -distancia);
	        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(distancia, -40, distancia);
	        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(distancia, distancia, distancia);
	        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(distancia, distancia, -distancia);	        
	        gl.glEnd();
	        
	        gl.glBegin(GL2.GL_QUADS);
	        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-distancia, -40, -distancia);
	        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-distancia, -40, distancia);
	        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-distancia, distancia, distancia);
	        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-distancia, distancia, -distancia);	        
	        gl.glEnd();	        
	        gl.glPopMatrix();
		} catch (IOException exc) {
			exc.printStackTrace();
			//System.exit(1);
		}
	}

}
