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
       // animator.stop();
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
    
    
//    public void box(GL gl, GLUquadric q, double width, double lenght, double height){
//        gl.glColor3f(1f,0f,0f); 
//        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
//        gl.glLineWidth(10);
//        glu.gluCylinder(q, width, lenght, height,4,1);
//        glu.gluDisk(q, 0, width, 4, 1);
//        
//        gl.glTranslated(0, 0, height);
//        glu.gluDisk(q, 0, width, 4, 1);
//        
//        gl.glTranslated(0, 0, -height);
//      
//        gl.glColor3f(1f,1f,1f);
//        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
//        
//        glu.gluCylinder(q, width, lenght, height,4,1);
//        glu.gluDisk(q, 0, width, 4, 1);
//        
//        gl.glTranslated(0, 0, height);
//        glu.gluDisk(q, 0, width, 4, 1);    
//    }
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        //gl.glColor3f(1f,0f,0f); 
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        
        GLUquadric q; 
        q = glu.gluNewQuadric();
        gl.glRotatef(-120, 1, 0, 0);
        glu.gluCylinder(q, 0.5f, 0.5f, 0.8f, 10, 1);

    // gl.glRotatef(120, 0, 0, 1);
//      
        //glu.gluDisk(q, 0.-25f, 0.025f, 1, 4);

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
