package com.theaemogie.timble.renderer;

import com.theaemogie.timble.components.SpriteRenderer;
import com.theaemogie.timble.timble.GameObject;
import com.theaemogie.timble.timble.Window;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:theaemogie@gmail.com"> Aemogie. </a>
 */
public class Renderer {
    private final int MAX_BATCH_SIZE = 1000;
    private List<RenderBatch> batches;
    private static Shader currentShader;

    public Renderer() {
        this.batches = new ArrayList<>();
    }

    public void add(GameObject go) {
        SpriteRenderer spriteRenderer = go.getComponent(SpriteRenderer.class);
        if (spriteRenderer != null) {
            add(spriteRenderer);
        }
    }

    private void add(SpriteRenderer spriteRenderer) {
        boolean added = false;
        for (RenderBatch batch : batches) {
            if (batch.hasRoom()) {
                Texture texture = spriteRenderer.getTexture();
                
                if (texture == null || (batch.hasTexture(texture) || batch.hasTextureRoom())) {
                    batch.addSprite(spriteRenderer);
                    added = true;
                    break;
                }
            }
        }

        if (!added) {
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE);
            newBatch.start();
            batches.add(newBatch);
            newBatch.addSprite(spriteRenderer);
        }
    }
    
    public static void bindShader(Shader shader) {
        currentShader = shader;
    }
    
    public static Shader getBoundShader() {
        return currentShader;
    }

    public void render(Window window) {
        currentShader.use();
        for (RenderBatch batch : batches) {
            batch.render(window);
        }
    }
}