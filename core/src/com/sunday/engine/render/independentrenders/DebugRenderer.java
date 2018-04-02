package com.sunday.engine.render.independentrenders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.render.IndependentRenderer;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

public class DebugRenderer extends IndependentRenderer {
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private int selectedMatrix;
    private boolean smallRenderingSelected;


    public DebugRenderer() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        selectedMatrix = 2;
        smallRenderingSelected = true;
    }

    @Override
    public void setCamera(Camera camera) {
        this.camera = (OrthographicCamera) camera;
    }

    @Override
    protected void renderInternal(float delta) {
        if (camera == null) {
            return;
        }
        Matrix4 matrix4;
        switch (selectedMatrix) {
            case 0:
                matrix4 = camera.projection;
                break;
            case 1:
                matrix4 = camera.view;
                break;
            case 2:
                matrix4 = camera.combined;
                break;
            case 3:
                matrix4 = camera.invProjectionView;
                break;
            default:
                matrix4 = camera.combined;
        }
        shapeRenderer.setProjectionMatrix(matrix4);
        shapeRenderer.begin();
        if (smallRenderingSelected) {
            smallRendering();
        } else {
            largerRendering();
        }
        shapeRenderer.end();
    }

    private void smallRendering() {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.line(0, -3000, 0, -3000);
        shapeRenderer.flush();
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.line(-3000, 0, 3000, 0);
        shapeRenderer.flush();
        shapeRenderer.setColor(Color.RED);
        renderMesh(0.1f);
        shapeRenderer.setColor(Color.YELLOW);
        renderMesh(0.2f);
        shapeRenderer.setColor(Color.GREEN);
        renderMesh(0.4f);
        shapeRenderer.setColor(Color.CYAN);
        renderMesh(0.6f);
        shapeRenderer.setColor(Color.BLUE);
        renderMesh(0.8f);
        shapeRenderer.setColor(Color.PURPLE);
        renderMesh(1.0f);
        shapeRenderer.setColor(Color.RED);
        renderMesh(10);
        shapeRenderer.setColor(Color.YELLOW);
        renderMesh(20);
        shapeRenderer.setColor(Color.GREEN);
        renderMesh(40);
        shapeRenderer.setColor(Color.CYAN);
        renderMesh(80);
        shapeRenderer.setColor(Color.BLUE);
        renderMesh(160);
        shapeRenderer.setColor(Color.PURPLE);
        renderMesh(320);
    }

    private void largerRendering() {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.line(0, 3000, 0, -3000);
        shapeRenderer.flush();
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.line(-3000, 0, 3000, 0);
        shapeRenderer.flush();
        shapeRenderer.setColor(Color.RED);
        renderMesh(10);
        shapeRenderer.setColor(Color.YELLOW);
        renderMesh(20);
        shapeRenderer.setColor(Color.GREEN);
        renderMesh(40);
        shapeRenderer.setColor(Color.CYAN);
        renderMesh(80);
        shapeRenderer.setColor(Color.BLUE);
        renderMesh(160);
        shapeRenderer.setColor(Color.PURPLE);
        renderMesh(640);
        shapeRenderer.setColor(Color.RED);
        renderMesh(2560);
    }

    private void renderMesh(float radius) {
        shapeRenderer.circle(0, 0, radius, 90);
        shapeRenderer.line(radius, -3000, radius, 3000);
        shapeRenderer.line(-radius, -3000, -radius, 3000);
        shapeRenderer.line(-3000, radius, 3000, radius);
        shapeRenderer.line(-3000, -radius, 3000, -radius);
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        systemPort.addDataInstance(new Rule(KeyBoardCondition.keyPressed("["), new Reaction<MetaDataContext<KeyBoard>>() {
            @Override
            public void accept(MetaDataContext<KeyBoard> metaDataContext) {
                selectedMatrix++;
                if (selectedMatrix == 4) selectedMatrix = 0;
                printOptions();
            }
        }));
        systemPort.addDataInstance(new Rule(KeyBoardCondition.keyPressed("]"), new Reaction<MetaDataContext<KeyBoard>>() {

            @Override
            public void accept(MetaDataContext<KeyBoard> metaDataContext) {
                smallRenderingSelected = !smallRenderingSelected;
                printOptions();
            }
        }));
    }

    private void printOptions() {
        System.out.println("DebugRender");
        String matrixName;
        switch (selectedMatrix) {
            case 0:
                matrixName = "projection";
                break;
            case 1:
                matrixName = "view";
                break;
            case 2:
                matrixName = "combined";
                break;
            case 3:
                matrixName = "invProjectionView";
                break;
            default:
                matrixName = "combined";
        }
        System.out.println("Selected matrix : " + matrixName);
        System.out.println("Rendering mode : " + (smallRenderingSelected ? "Small" : "Lager"));
    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
