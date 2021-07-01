package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {
    
    double inner = 0.1;
    double outer = 0.3;
    double teeth_len = 0.2;
    int teeth_count = 7;
    double height = 0.3;
    
    int x_r = 0;
    int y_r = 0;
    int z_r = 0;

    boolean type = Boolean.TRUE;
    
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

     public static void gear(GL gl,
                          double inner_radius,
                          double outer_radius,
                          double teeth_lenght,
                         double height,
                          int teeth, 
                          int mode)
  {
    int i;
    double r0, r1, r2,inner_x1,inner_y1,inner_x2,inner_y2,outer_x1,outer_y1,outer_x2,outer_y2,inner_x3,inner_y3,inner_x4,inner_y4;
    double angle=0, da=2 * Math.PI / (teeth * 2);

    r0 = inner_radius;
    r1 = outer_radius - inner_radius;
    r2 = outer_radius + teeth_lenght;

    

    /* draw front face */
   
    for (i = 0; i <= teeth*2; i++)
      {      
        if(i % 2 == 0){
            //–исуем тонкую часть шестеренки
            
            inner_x1 = inner_radius*Math.cos(angle);
            inner_y1 = inner_radius*Math.sin(angle);

            outer_x1 = outer_radius*Math.cos(angle);
            outer_y1 = outer_radius*Math.sin(angle);
            
            inner_x2 = inner_radius*Math.cos(angle+da);
            inner_y2 = inner_radius*Math.sin(angle+da);

            outer_x2 = outer_radius*Math.cos(angle+da);
            outer_y2 = outer_radius*Math.sin(angle+da);
            gl.glColor3d(1,1,1);

            //верхн€€ плоскость
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,0);
                gl.glVertex3d(outer_x1, outer_y1,0);

                gl.glVertex3d(outer_x2, outer_y2,0);
                gl.glVertex3d(inner_x2, inner_y2,0);
            gl.glEnd();

            //нижн€€ плоскость
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,height);
                gl.glVertex3d(outer_x1, outer_y1,height);

                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(inner_x2, inner_y2,height);
            gl.glEnd();
            gl.glColor3d(Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)));

            //передний торец
            gl.glBegin(mode);
                gl.glVertex3d(outer_x1, outer_y1,0);
                gl.glVertex3d(outer_x1, outer_y1,height);
                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,0);
            gl.glEnd();
            
            //задний торец
            gl.glBegin(mode);
                gl.glColor3d(Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)));
                gl.glVertex3d(inner_x1, inner_y1,0);
                gl.glVertex3d(inner_x1, inner_y1,height);
                gl.glColor3d(Math.abs(Math.cos(angle+da)), Math.abs(Math.cos(angle+da)), Math.abs(Math.cos(angle+da)));

                gl.glVertex3d(inner_x2, inner_y2,height);
                gl.glVertex3d(inner_x2, inner_y2,0);
            gl.glEnd();
            
            
//            //бок 1
//            gl.glBegin(mode);
//            gl.glVertex3d(inner_x1, inner_y1,0);
//            gl.glVertex3d(inner_x1, inner_y1,height);
//            gl.glVertex3d(outer_x1, outer_y1,height);
//            gl.glVertex3d(outer_x1, outer_y1,0);
//            gl.glEnd();
//            
//            //бок 2
//            gl.glBegin(mode);
//            gl.glVertex3d(inner_x2, inner_y2,0);
//            gl.glVertex3d(inner_x2, inner_y2,height);
//            gl.glVertex3d(outer_x2, outer_y2,height);
//            gl.glVertex3d(outer_x2, outer_y2,0);
//            gl.glEnd();
        }
        else{ 
            // –исуем зубцы шестеренки
            inner_x1 = inner_radius*Math.cos(angle);
            inner_y1 = inner_radius*Math.sin(angle);

            outer_x1 = r2*Math.cos(angle);
            outer_y1 = r2*Math.sin(angle);
            
            inner_x2 = inner_radius*Math.cos(angle+da);
            inner_y2 = inner_radius*Math.sin(angle+da);

            outer_x2 = r2*Math.cos(angle+da);
            outer_y2 = r2*Math.sin(angle+da);
            
            inner_x3 = outer_radius*Math.cos(angle);
            inner_y3 = outer_radius*Math.sin(angle);
            
            inner_x4 = outer_radius*Math.cos(angle+da);
            inner_y4 = outer_radius*Math.sin(angle+da);  
            gl.glColor3d(1,1,1);

            //верхн€€ плоскость
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,0);
                gl.glVertex3d(outer_x1, outer_y1,0);

                gl.glVertex3d(outer_x2, outer_y2,0);
                gl.glVertex3d(inner_x2, inner_y2,0);
            gl.glEnd();

            //нижн€€ плоскость
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,height);
                gl.glVertex3d(outer_x1, outer_y1,height);

                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(inner_x2, inner_y2,height);
            gl.glEnd();
            
            //передний торец
            gl.glBegin(mode);
                gl.glColor3d(Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)));
                gl.glVertex3d(outer_x1, outer_y1,0);
                gl.glVertex3d(outer_x1, outer_y1,height);
                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,0);
            gl.glEnd();
            //задний торец
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,0);
                gl.glVertex3d(inner_x1, inner_y1,height);
                gl.glColor3d(Math.abs(Math.cos(angle+da)), Math.abs(Math.cos(angle+da)), Math.abs(Math.cos(angle+da)));

                gl.glVertex3d(inner_x2, inner_y2,height);
                gl.glVertex3d(inner_x2, inner_y2,0);
            gl.glEnd();
            
            
            //бок 1
            gl.glBegin(mode);
                gl.glVertex3d(inner_x3, inner_y3,0);
                gl.glVertex3d(inner_x3, inner_y3,height);
                gl.glVertex3d(outer_x1, outer_y1,height);
                gl.glVertex3d(outer_x1, outer_y1,0);
            gl.glEnd();
            
            //бок 2
            gl.glBegin(mode);
                gl.glVertex3d(inner_x2, inner_y2,0);
                gl.glVertex3d(inner_x2, inner_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,0);
            gl.glEnd();
        }
 
        angle = angle + da;
        
        
      }
    
  }
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
       // gl.glLoadIdentity();
        gl.glEnable(GL.GL_DEPTH_TEST);
        // Move the "drawing cursor" around
        //gl.glTranslatef(-1.5f, 0.0f, -6.0f);

        int mode = gl.GL_POLYGON;
        if (type) mode = gl.GL_POLYGON;
        else mode = gl.GL_LINE_LOOP;

        gl.glRotated(x_r, 1, 0, 0);
        gl.glRotated(y_r, 0, 1, 0);
        gl.glRotated(z_r, 0, 0, 1);
        
        gear(gl, inner, outer, teeth_len, height, teeth_count, mode);

        x_r = 0;
        y_r = 0;
        z_r = 0;
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

