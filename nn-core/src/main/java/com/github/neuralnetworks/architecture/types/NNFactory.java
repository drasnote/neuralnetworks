package com.github.neuralnetworks.architecture.types;

import com.github.neuralnetworks.architecture.Layer;
import com.github.neuralnetworks.calculation.neuronfunctions.AparapiSigmoid;
import com.github.neuralnetworks.calculation.neuronfunctions.AparapiSoftReLU;
import com.github.neuralnetworks.calculation.neuronfunctions.AparapiStochasticBinary;
import com.github.neuralnetworks.calculation.neuronfunctions.AparapiTanh;
import com.github.neuralnetworks.training.random.AparapiXORShiftInitializer;

/**
 * Factory class for neural networks
 */
public class NNFactory {

    public static MultiLayerPerceptron mlp(Layer[] layers, boolean addBias) {
	if (layers.length <= 1) {
	    throw new IllegalArgumentException("more than one layer is required");
	}

	MultiLayerPerceptron result = new MultiLayerPerceptron();
	for (int i = 0; i < layers.length; i++) {
	    result.addLayer(layers[i], addBias);
	}

	return result;
    }

    public static MultiLayerPerceptron mlpSigmoid(int[] layers, boolean addBias) {
	if (layers.length <= 1) {
	    throw new IllegalArgumentException("more than one layer is required");
	}

	MultiLayerPerceptron result = new MultiLayerPerceptron();
	result.addLayer(new Layer(layers[0], new AparapiSigmoid()), false);
	for (int i = 1; i < layers.length; i++) {
	    result.addLayer(new Layer(layers[i], new AparapiSigmoid()), addBias);
	}

	return result;
    }

    public static MultiLayerPerceptron mlpRelu(int[] layers, boolean addBias) {
	if (layers.length <= 1) {
	    throw new IllegalArgumentException("more than one layer is required");
	}
	
	MultiLayerPerceptron result = new MultiLayerPerceptron();
	result.addLayer(new Layer(layers[0], new AparapiSoftReLU()), false);
	for (int i = 1; i < layers.length; i++) {
	    result.addLayer(new Layer(layers[i], new AparapiSoftReLU()), addBias);
	}
	
	return result;
    }

    public static MultiLayerPerceptron mlpTanh(int[] layers, boolean addBias) {
	if (layers.length <= 1) {
	    throw new IllegalArgumentException("more than one layer is required");
	}

	MultiLayerPerceptron result = new MultiLayerPerceptron();
	result.addLayer(new Layer(layers[0], new AparapiTanh()), false);
	for (int i = 1; i < layers.length; i++) {
	    result.addLayer(new Layer(layers[i], new AparapiTanh()), addBias);
	}

	return result;
    }

    public static Autoencoder autoencoderSigmoid(int visibleCount, int hiddenCount, boolean addBias) {
	return new Autoencoder(new Layer(visibleCount), new Layer(hiddenCount, new AparapiSigmoid()), new Layer(visibleCount, new AparapiSigmoid()), addBias);
    }
    
    public static Autoencoder autoencoderReLU(int visibleCount, int hiddenCount, boolean addBias) {
	return new Autoencoder(new Layer(visibleCount), new Layer(hiddenCount, new AparapiSoftReLU()), new Layer(visibleCount, new AparapiSoftReLU()), addBias);
    }
    
    public static Autoencoder autoencoderTanh(int visibleCount, int hiddenCount, boolean addBias) {
	return new Autoencoder(new Layer(visibleCount), new Layer(hiddenCount, new AparapiTanh()), new Layer(visibleCount, new AparapiTanh()), addBias);
    }

    public static RBM rbmSigmoidSigmoid(int visibleCount, int hiddenCount, boolean addBias) {
	return new RBM(new Layer(visibleCount, new AparapiSigmoid()), new Layer(hiddenCount, new AparapiSigmoid()), addBias, addBias);
    }

    public static RBM rbmReluRelu(int visibleCount, int hiddenCount, boolean addBias) {
	return new RBM(new Layer(visibleCount, new AparapiSoftReLU()), new Layer(hiddenCount, new AparapiSoftReLU()), addBias, addBias);
    }

    public static RBM rbmTanhTanh(int visibleCount, int hiddenCount, boolean addBias) {
	return new RBM(new Layer(visibleCount, new AparapiTanh()), new Layer(hiddenCount, new AparapiTanh()), addBias, addBias);
    }

    public static RBM rbmSigmoidBinary(int visibleCount, int hiddenCount, boolean addBias) {
	return new RBM(new Layer(visibleCount, new AparapiSigmoid()), new Layer(hiddenCount, new AparapiStochasticBinary(new AparapiXORShiftInitializer())), addBias, addBias);
    }

    public static RBM rbmReluBinary(int visibleCount, int hiddenCount, boolean addBias) {
	return new RBM(new Layer(visibleCount, new AparapiSoftReLU()), new Layer(hiddenCount, new AparapiStochasticBinary(new AparapiXORShiftInitializer())), addBias, addBias);
    }

    public static RBM rbmTanhBinary(int visibleCount, int hiddenCount, boolean addBias) {
	return new RBM(new Layer(visibleCount, new AparapiTanh()), new Layer(hiddenCount, new AparapiStochasticBinary(new AparapiXORShiftInitializer())), addBias, addBias);
    }

    public static SupervisedRBM srbmSigmoidSigmoid(int visibleCount, int hiddenCount, int dataCount, boolean addBias) {
	return new SupervisedRBM(new Layer(visibleCount, new AparapiSigmoid()), new Layer(hiddenCount, new AparapiSigmoid()), new Layer(dataCount), addBias, addBias);
    }

    public static SupervisedRBM srbmSigmoidBinary(int visibleCount, int hiddenCount, int dataCount, boolean addBias) {
	return new SupervisedRBM(new Layer(visibleCount, new AparapiSigmoid()), new Layer(hiddenCount, new AparapiStochasticBinary(new AparapiXORShiftInitializer())), new Layer(dataCount), addBias, addBias);
    }

    public static SupervisedRBM srbmReluBinary(int visibleCount, int hiddenCount, int dataCount, boolean addBias) {
	return new SupervisedRBM(new Layer(visibleCount, new AparapiSoftReLU()), new Layer(hiddenCount, new AparapiStochasticBinary(new AparapiXORShiftInitializer())), new Layer(dataCount), addBias, addBias);
    }

    public static SupervisedRBM srbmReluRelu(int visibleCount, int hiddenCount, int dataCount, boolean addBias) {
	return new SupervisedRBM(new Layer(visibleCount, new AparapiSoftReLU()), new Layer(hiddenCount, new AparapiSoftReLU()), new Layer(dataCount), addBias, addBias);
    }

    public static SupervisedRBM srbmTanhTanh(int visibleCount, int hiddenCount, int dataCount, boolean addBias) {
	return new SupervisedRBM(new Layer(visibleCount, new AparapiTanh()), new Layer(hiddenCount, new AparapiTanh()), new Layer(dataCount), addBias, addBias);
    }
    
    public static DBN dbnSigmoid(int[] layers, boolean addBias) {
	if (layers.length <= 1) {
	    throw new IllegalArgumentException("more than one layer is required");
	}

	DBN result = new DBN();
	for (int i = 0; i < layers.length; i++) {
	    result.addLayer(new Layer(layers[i], new AparapiSigmoid()), addBias);
	}

	return result;
    }
    
    public static DBN dbnReLU(int[] layers, boolean addBias) {
	if (layers.length <= 1) {
	    throw new IllegalArgumentException("more than one layer is required");
	}
	
	DBN result = new DBN();
	for (int i = 0; i < layers.length; i++) {
	    result.addLayer(new Layer(layers[i], new AparapiSoftReLU()), addBias);
	}
	
	return result;
    }

    public static DBN dbnTanh(int[] layers, boolean addBias) {
	if (layers.length <= 1) {
	    throw new IllegalArgumentException("more than one layer is required");
	}
	
	DBN result = new DBN();
	for (int i = 0; i < layers.length; i++) {
	    result.addLayer(new Layer(layers[i], new AparapiTanh()), addBias);
	}
	
	return result;
    }

    public static StackedAutoencoder saeSigmoid(int[] layers, boolean addBias) {
	if (layers == null || layers.length <= 1) {
	    throw new IllegalArgumentException("more than one layer is required");
	}

	StackedAutoencoder result = new StackedAutoencoder(new Layer(layers[0], new AparapiSigmoid()));
	for (int i = 1; i < layers.length; i++) {
	    result.addLevel(new Layer(layers[i], new AparapiSigmoid()), new Layer(new AparapiSigmoid()), addBias);
	}

	return result;
    }

    public static StackedAutoencoder saeReLU(int[] layers, int hiddenCount, boolean addBias) {
	if (layers == null || layers.length <= 1) {
	    throw new IllegalArgumentException("more than one layer is required");
	}

	StackedAutoencoder result = new StackedAutoencoder(new Layer(layers[0], new AparapiSoftReLU()));
	for (int i = 1; i < layers.length; i++) {
	    result.addLevel(new Layer(layers[i], new AparapiSoftReLU()), new Layer(new AparapiSoftReLU()), addBias);
	}

	return result;
    }
    
    public static StackedAutoencoder saeTanh(int[] layers, int hiddenCount, boolean addBias) {
	if (layers == null || layers.length <= 1) {
	    throw new IllegalArgumentException("more than one layer is required");
	}
	
	StackedAutoencoder result = new StackedAutoencoder(new Layer(layers[0], new AparapiTanh()));
	for (int i = 1; i < layers.length; i++) {
	    result.addLevel(new Layer(layers[i], new AparapiTanh()), new Layer(new AparapiTanh()), addBias);
	}
	
	return result;
    }
}
