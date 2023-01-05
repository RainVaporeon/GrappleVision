package com.spiritlight.grapplevisualizer.config;

import java.io.*;

public class ModInitializer implements Serializable {
    public static final ModInitializer DEFAULT_STATE = new ModInitializer(38.0d, DisplayMode.DISABLED);

    private double maxReachDistance;
    private DisplayMode mode;

    public ModInitializer(double maxReachDistance, DisplayMode mode) {
        this.maxReachDistance = maxReachDistance;
        this.mode = mode;
    }

    public double getMaxReachDistance() {
        return maxReachDistance;
    }

    public void setMaxReachDistance(double maxReachDistance) {
        if(maxReachDistance <= 0) throw new IllegalArgumentException("maxReachDistance out of range: " + maxReachDistance);
        this.maxReachDistance = maxReachDistance;
    }

    public DisplayMode getMode() {
        return mode;
    }

    public void setMode(DisplayMode mode) {
        if(mode == null) throw new NullPointerException("Mode supplied cannot be null!");
        this.mode = mode;
    }

    public void serialize() {
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos);
             FileOutputStream fos = new FileOutputStream("gv.config")) {
            oos.writeObject(this);
            fos.write(baos.toByteArray());
        } catch (FileNotFoundException fnfe) {
            System.err.println("Error: gv.config seems to exist as a directory.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    public static ModInitializer deserialize() {
        File f = new File("gv.config");
        if(!f.exists()) {
            return DEFAULT_STATE;
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gv.config"))) {
            Object o = ois.readObject();
            if(o instanceof ModInitializer) {
                return (ModInitializer) o;
            }
            throw new Error("Received unexpected class instance " + o.getClass());
        } catch(EOFException eof) {
            eof.printStackTrace();
            System.out.println("Handled by deleting gv.config...");
            new File("gv.config").delete();
            return DEFAULT_STATE;
        } catch (FileNotFoundException fnfe) {
            System.err.println("Error: gv.config seems to exist as a directory.");
            throw new IllegalStateException(fnfe);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            throw new Error("Expected " + ModInitializer.class.getCanonicalName() + ", received wrong class!");
        }
    }
}
