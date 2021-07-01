package org.yourorghere;

import java.io.Console;
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
    int x_r = 45;
    int y_r = 0;
    int z_r = 45;
    
    int x_t = -1;
    int y_t = 0;
    int z_t = -1; 
    
    int size = 80; 
    
    int[][] colors = {{0,200,0},{0,200,0},
        {122,94,63}, {122,94,63}, 
        {74,59,45}, {74,59,45},
        {96,84,76},{96,84,76},
        {134,82,38},{134,82,38},
        {23,18,24},  {23,18,24},
        {185,128,92}, {185,128,92}};
    int layer_num = colors.length;

    double[][][][] ground = new double[14][size][size][2];
    
    double radius = 1.0; 
    double step = radius / size; 
    
   
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
        
        // Генерируем цвета для блоков
        for (int i = 0; i < layer_num; i++){
            for (int col = 0; col < size; col++){
                for (int row = 0; row < size; row++){
                    ground[i][col][row][0] = (double)get_random_color(i);
                    ground[i][col][row][1] = get_random_offset(row, col, i);
                }
            } 
        }
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
    }
    
    public int get_random_color(int layer){
        double p = Math.random();
        
        if(p >0.97){
            if (layer == colors.length-1){
                return layer-1;
            }
            return layer+1;
        }
        else if(p < 0.01){
            if (layer == 0){
                return layer+1;
            }
            return layer-1;
        }
        else return layer; 
    }
    public double get_random_offset(int row, int column, int layer){
        double offset = 0.0;
        if (layer == 0){
            double p = Math.random();
        
        double sum_offset = 0.0;
//        sum_offset = ground[layer-1][column-1][row-1][1] + 
//                ground[layer-1][column][row-1][1] +
//                ground[layer-1][column-1][row][1] +
//                ground[layer-1][column+1][row-1][1] +
//                ground[layer-1][column+1][row][1] +
//                ground[layer-1][column+1][row+1][1] + 
//                ground[layer-1][column-1][row-1][1] + 
//                ground[layer-1][column-1][row-1][1];
        if (p > 0.5){
            sum_offset += step;
        }
        if (p > 0.8){
            sum_offset += step*3;
        }
        //sum_offset = p* 100 * step;
        //System.out.println(sum_offset);
        return sum_offset; 
        }
        
        if (ground[layer-1][column][row][1] != 0.0){
            return ground[layer-1][column][row][1];
        }
        
        return 0.0;
    }
    
    public void draw_box(GL gl, int layer, int row, int column){
        double x1 = column*step, y1 = row*step, z1 = layer*step;
        
        layer = (int)ground[layer][column][row][0];
        double offset = ground[layer][column][row][1];
        z1 += offset;
        gl.glColor3d(colors[layer][0]/255.0,colors[layer][1]/255.0 ,colors[layer][2]/255.0);
        
        //Нижняя  сторона - РАБОТАЕТ
        gl.glBegin(GL.GL_POLYGON);
            gl.glVertex3d(x1, y1, z1);
            gl.glVertex3d(x1, y1, z1);
            gl.glVertex3d(x1+step, y1, z1);    
            gl.glVertex3d(x1+step, y1, z1); 
        gl.glEnd();
        
        //Нижняя  сторона - РАБОТАЕТ
        gl.glBegin(GL.GL_POLYGON);
            gl.glVertex3d(x1, y1, z1+step);
            gl.glVertex3d(x1, y1, z1);
            gl.glVertex3d(x1+step, y1, z1);    
            gl.glVertex3d(x1+step, y1, z1+step); 
        gl.glEnd();
        
        //Передняя сторона - РАБОТАЕТ
        gl.glBegin(GL.GL_POLYGON);
            gl.glVertex3d(x1, y1, z1);   
            gl.glVertex3d(x1+step, y1,z1);
            gl.glVertex3d(x1+step, y1+step,z1); 
            gl.glVertex3d(x1, y1+step, z1);
        gl.glEnd();


        // Боковая право сторона - РАБОТАЕТ
        gl.glBegin(GL.GL_POLYGON);
            gl.glVertex3d(x1+step, y1,z1);
            gl.glVertex3d(x1+step, y1,z1+step);
            gl.glVertex3d(x1+step, y1+step,z1+step);
            gl.glVertex3d(x1+step, y1+step,z1); 
        gl.glEnd();
        
        // Боковая лево сторона - РАБОТАЕТ
        gl.glBegin(GL.GL_POLYGON);
            gl.glVertex3d(x1, y1,z1);
            gl.glVertex3d(x1, y1,z1+step);
            gl.glVertex3d(x1, y1+step,z1+step);
            gl.glVertex3d(x1, y1+step,z1); 
        gl.glEnd();
        
        //Задняя сторона - РАБОТАЕТ
//        gl.glVertex3d(x1+step, y1,z1+step);
//        gl.glVertex3d(x1+step, y1+step,z1+step); 
//        
//        gl.glVertex3d(x1, y1+step,z1+step); 
//        gl.glVertex3d(x1, y1,z1+step);
//        gl.glVertex3d(x1, y1,z1+step);
//        gl.glVertex3d(x1, y1+step,z1+step); 
//        gl.glVertex3d(x1, y1, z1);   
//        gl.glVertex3d(x1+step, y1,z1);
    }
    
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glLoadIdentity();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        gl.glRotated(x_r, 1, 0, 0);
        gl.glRotated(y_r, 0, 1, 0);
        gl.glRotated(z_r, 0, 0, 1);
        
        gl.glTranslated(x_t, 0, 0);
        gl.glTranslated(0, y_t, 0);
        gl.glTranslated(0, 0, z_t);
        
        for (int i = 0; i < layer_num; i++){
            for (int col = 0; col < size; col++){
                for (int row = 0; row < size; row++){
                    draw_box(gl, i, row, col);
                }
            }            
        }
               
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

