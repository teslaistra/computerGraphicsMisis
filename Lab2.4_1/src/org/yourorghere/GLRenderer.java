package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;


/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {
    GLU glu = new GLU();

    int x_r = 0;
    int y_r = 0;
    int z_r = 0;

    
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
        GLU glu = new GLU();

//        if (height <= 0) { // avoid a divide by zero error!
//        
//            height = 1;
//        }
//        final float h = (float) width / (float) height;
//        gl.glViewport(0, 0, width, height);
//        gl.glMatrixMode(GL.GL_PROJECTION);
//        gl.glLoadIdentity();
//        glu.gluPerspective(45.0f, h, 1.0, 20.0);
//        gl.glMatrixMode(GL.GL_MODELVIEW);
//        gl.glLoadIdentity();
    }

    public void cylinder(GLUquadric q, GL gl, double radius, double height){
        gl.glPushMatrix();
        glu.gluDisk(q, 0, radius, 20, 1);
        glu.gluCylinder(q, radius, radius, height, 20, 1);
        gl.glTranslated(height, 0, 0);
        glu.gluDisk(q, 0, radius, 20, 1);
        gl.glTranslated(-height, 0, 0);
        gl.glPopMatrix();
        
    }
    
    public void draw_all(GLUquadric q, GL gl) {
        gl.glPushMatrix();
        gl.glTranslated(0, 0, -0.8);
        cylinder(q,gl, 0.01f, 1.6f);
        gl.glPopMatrix();
        
        //крайне левый блин
        gl.glPushMatrix();
        gl.glTranslated(0, 0, -0.6);
        cylinder(q, gl, 0.1, 0.05);
        gl.glPopMatrix();
        
        //левый блин
        gl.glPushMatrix();
        gl.glTranslated(0, 0, -0.55);
        cylinder(q, gl, 0.15, 0.1);
        gl.glPopMatrix();
        
        //крайне правый блин
        gl.glPushMatrix();
        gl.glTranslated(0, 0, 0.6-0.15);
        cylinder(q, gl, 0.15, 0.1);
        gl.glPopMatrix();
        
        //правый блин
        gl.glPushMatrix();
        gl.glTranslated(0, 0, 0.6-0.05);
        cylinder(q, gl, 0.1, 0.05);
        gl.glPopMatrix();
    }
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        //gl.glLoadIdentity();

        GLUquadric q; 
        q = glu.gluNewQuadric();
        
        gl.glColor3f(1f,0f,0f);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
        draw_all(q, gl);
        
        gl.glColor3f(0f,0f,1f);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        draw_all(q, gl);
        
        gl.glRotated(x_r, 1, 0, 0);
        gl.glRotated(y_r, 0, 1, 0);
        gl.glRotated(z_r, 0, 0, 1);
             
        x_r = 0;
        y_r = 0;
        z_r = 0;
       
       
//       gl.glEnable(GL.GL_LIGHTING); 
//gl.glEnable(GL.GL_LIGHT0);  
//gl.glEnable(GL.GL_NORMALIZE); 
//
//float[] ambientLight = { 0.1f, 0.f, 0.f,0f };  // weak RED ambient 
//gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambientLight, 0); 
//
//float[] diffuseLight = { 1f,2f,1f,0f };  // multicolor diffuse 
//gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuseLight, 0); 
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

