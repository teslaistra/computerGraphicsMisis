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



/**
 * Lab21.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Lab21 implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Lab21());
        frame.add(canvas);
        frame.setSize(700, 700);
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
        animator.stop();
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
    
    public static void sphere(GL gl, double X, double Y, double Z, double R){
        double alpha,x,y,z, da,beta,db;
        int n,m;
        
        n=24;
        m=24; 
        da = 2 * Math.PI/n;
        db = Math.PI/m;
        beta =-Math.PI/2; 
        while (beta <= Math.PI/2) {
            alpha = 0;
            gl.glBegin(GL.GL_QUAD_STRIP);
            while (alpha < Math.PI*2+da) {
                x = R* Math.cos(beta) * Math.cos(alpha);
                y = R* Math.cos(beta) * Math.sin(alpha);
                z = R* Math.sin(beta);
                gl.glVertex3d(X+x, Y+y,Z+ z);
                
                x = R* Math.cos(beta+db) * Math.cos(alpha);
                y = R* Math.cos(beta+db) * Math.sin(alpha);
                z = R* Math.sin(beta+db);
                gl.glVertex3d(X+x, Y+y, Z+z);
                
                alpha = alpha +da;
            }
            beta = beta +db;
            gl.glEnd();
            
        }
        
        
    }
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(gl.GL_DEPTH_TEST);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glRotatef(45, 1, 0, 0);
        gl.glRotatef(45, 0, 0, 1);

        // Move the "drawing cursor" around

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
        
        

        for (double i= -0.6; i < 0.8; i = i + 0.1)
        {
            gl.glColor3d(0, 1, 0);
            sphere(gl, i, 0, 0, 0.01);
            
            gl.glColor3d(1, 0, 0);
            sphere(gl, 0, i, 0, 0.01);

            gl.glColor3d(0, 0, 1);
            sphere(gl, 0, 0, i, 0.01);
        }


        
        
        
        
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

