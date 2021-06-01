package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;



/**
 * Lab24.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Lab24 implements GLEventListener {
    
    GLU glu = new GLU();
    private float rpoly;
    
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Lab24());
        frame.add(canvas);
        frame.setSize(640, 640);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
    }
    public void coord(GL gl){
              
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();


        // Move the "drawing cursor" around
 gl.glRotatef(45, 1, 0, 0);
       gl.glRotatef(45, 0, 0, 1);
        // Drawing Using Triangles
        gl.glBegin(GL.GL_LINES);
            gl.glColor3f(0.0f, 1.0f, 0);    // Set the current drawing color to red
            gl.glVertex3f(-0.8f, 0.0f, 0.0f);   // Top
            gl.glVertex3f(0.8f, 0.0f, 0.0f);
            
            gl.glColor3f(1.0f, 0.0f, 0.0f);    // Set the current drawing color to red
            gl.glVertex3f(0, -0.8f, 0.0f);   // Top
            gl.glVertex3f(0, 0.8f, 0.0f); // Bottom Left
            
            gl.glColor3f(0.0f,0, 1.0f);    // Set the current drawing color to red
            gl.glVertex3f(0,0 , -0.8f);   // Top
            gl.glVertex3f(0, 0, 0.8f); // Bottom Left
        // Finished Drawing The Triangle
        gl.glEnd();
        double alpha = 0, R = 0.025, x, y;
        int n = 16;
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glColor3f(0, 0, 1.0f);
            gl.glVertex3d(0, 0, -0.8);
            for(int i=0;i<=n; i++){
                alpha = (float)(i*2*Math.PI/n);
                x = (float) (R*Math.cos(alpha));
                y = (float) (R*Math.sin(alpha));
                gl.glColor3d(0, 0, Math.abs(Math.cos(alpha)));
                gl.glVertex3d(x, y, -0.7);
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glColor3f(1, 0, 0.0f);
            gl.glVertex3d(0, -0.8, 0);
            for(int i=0;i<=n; i++){
                alpha = (float)(i*2*Math.PI/n);
                x = (float) (R*Math.cos(alpha));
                y = (float) (R*Math.sin(alpha));
                gl.glColor3d(Math.abs(Math.cos(alpha)), 0, 0);
                gl.glVertex3d(x, -0.7, y);
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glColor3f(0, 1, 0.0f);
            gl.glVertex3d(-0.8, 0, 0);
            for(int i=0;i<=n; i++){
                alpha = (float)(i*2*Math.PI/n);
                x = (float) (R*Math.cos(alpha));
                y = (float) (R*Math.sin(alpha));
                gl.glColor3d(0, Math.abs(Math.cos(alpha)), 0);
                gl.glVertex3d(-0.7, x, y);
            }
        gl.glEnd();
               // gl.glLoadIdentity();

    }
    public void display(GLAutoDrawable drawable) {
      GL gl = drawable.getGL();
      gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
      gl.glEnable(gl.GL_DEPTH_TEST);
      coord(gl);
      gl.glColor3f(1f,0f,0f); //applying red
                 gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);

      GLUquadric q; 
      q = glu.gluNewQuadric();
      
      //gl.glLoadIdentity();       // Reset The View    
      gl.glTranslatef(0, 0.5f, 0);
      gl.glRotatef(45, 0, 0, 1);

      glu.gluDisk(q, 0, 0.05f, 4, 0);
      glu.gluCylinder(q, 0.05f, 0.05f, -0.25f,4,1);
      gl.glTranslatef(0.25f, 0, 0);
      glu.gluDisk(q, 0, 0.05f, 4, 0);
      gl.glTranslatef(-0.25f, 0, 0);

     
      gl.glRotatef(-45, 0, 0, 1);
      gl.glTranslatef(0, -0.5f, 0);

      gl.glTranslatef(0, -0.5f, 0);
      gl.glRotatef(45, 0, 0, 1);
      gl.glColor3f(1f,1f,0f); //applying red

      //rpoly += 0.5f;  //assigning the angle 
      glu.gluDisk(q, 0, 0.05f, 4, 0);
      glu.gluCylinder(q, 0.05f, 0.05f, -0.25f,4,1);
      gl.glTranslatef(0.25f, 0, 0);
      glu.gluDisk(q, 0, 0.05f, 4, 0);
      gl.glTranslatef(-0.25f, 0, 0);
     
     
     // glu.gluSphere(q, 0.5f, 15, 15);
      //
     // gl.glTranslated(0.5, 0, 0);
     
      
       
//      gl.glEnable( GL.GL_LIGHTING );  
//      gl.glEnable( GL.GL_LIGHT0 );  
//      gl.glEnable( GL.GL_NORMALIZE );  
//
//      // weak RED ambient 
//      float[] ambientLight = { 0.1f, 0.f, 0.f,0f };  
//      gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambientLight, 0);  
//
//      // multicolor diffuse 
//      float[] diffuseLight = { 1f,2f,1f,0f };  
//      gl.glLightfv( GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuseLight, 0 ); 
//  gl.glLoadIdentity();


        // Move the "drawing cursor" around
// gl.glRotatef(45, 1, 0, 0);
 //       gl.glRotatef(45, 0, 0, 1);
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    
}

}
