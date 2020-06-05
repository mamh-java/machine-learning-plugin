package io.jenkins.plugins.ml;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPythonKernelInterpreterTest {


    private KernelInterpreter kernelInterpreter;

    @Before
    public void setup(){
        kernelInterpreter = new IPythonKernelInterpreter();
    }

    /**
     * ToString test for {@link IPythonKernelInterpreter}
     */

    @Test
    public void testToString() {
        if(kernelInterpreter instanceof IPythonKernelInterpreter){
            Assert.assertEquals("IPython is not properly initiated","IPython Interpreter",kernelInterpreter.toString());
        }
        Assert.assertNotNull("Interpreter is not up",kernelInterpreter);
    }

}
