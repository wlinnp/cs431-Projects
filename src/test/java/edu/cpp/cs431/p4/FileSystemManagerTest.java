package edu.cpp.cs431.p4;

import edu.cpp.cs431.p4.FileSystemManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author waiphyo
 *         2/28/17.
 */
public class FileSystemManagerTest {
    private FileSystemManager fileSystemManager;
    @Before
    public void init() {
        fileSystemManager = new FileSystemManager();
    }

    @Test
    public void Test1() {
        Assert.assertTrue(fileSystemManager.addFile("File 1", 3));
        Assert.assertTrue(7 == fileSystemManager.getBitMap());
        Assert.assertTrue(fileSystemManager.addFile("File 2", 4));
        Assert.assertTrue(127 == fileSystemManager.getBitMap());
    }

    @Test
    public void Test2() {
        Assert.assertTrue(fileSystemManager.addFile("File 1", 3));
        Assert.assertTrue(fileSystemManager.addFile("File 2", 2));
        Assert.assertTrue(fileSystemManager.addFile("File 3", 2));
        Assert.assertTrue(fileSystemManager.removeFile("File 2"));
        Assert.assertTrue(103 == fileSystemManager.getBitMap());
    }

    @Test
    public void Test3() {
        Assert.assertTrue(fileSystemManager.addFile("File 1", 3));
        Assert.assertTrue(fileSystemManager.addFile("File 2", 4));
        Assert.assertTrue(fileSystemManager.addFile("File 3", 3));
        Assert.assertTrue(fileSystemManager.removeFile("File 2"));
        Assert.assertTrue(fileSystemManager.addFile("File 4", 2));
        Assert.assertTrue(927 == fileSystemManager.getBitMap());
    }

    @Test
    public void Test4() {
        Assert.assertTrue(fileSystemManager.addFile("File 1", 3));
        Assert.assertTrue(fileSystemManager.addFile("File 2", 1));
        Assert.assertTrue(fileSystemManager.addFile("File 3", 3));
        Assert.assertTrue(fileSystemManager.removeFile("File 2"));
        Assert.assertTrue(fileSystemManager.addFile("File 4", 2));
        Assert.assertTrue(255 == fileSystemManager.getBitMap());
    }

    @Test
    public void Test5() {
        Assert.assertTrue(fileSystemManager.addFile("File 1", 3));
        Assert.assertTrue(fileSystemManager.addFile("File 2", 1));
        Assert.assertTrue(fileSystemManager.addFile("File 3", 3));
        Assert.assertTrue(fileSystemManager.addFile("File 4", 1));
        Assert.assertTrue(fileSystemManager.removeFile("File 2"));
        Assert.assertTrue(fileSystemManager.removeFile("File 4"));
        Assert.assertTrue(fileSystemManager.addFile("File 4", 3));
        Assert.assertTrue(511 == fileSystemManager.getBitMap());
    }

    @Test
    public void Test6() {
        for (int i = 0; i < 16; i++) {
            Assert.assertTrue(fileSystemManager.addFile("File " + i, 4));
        }
        Assert.assertFalse(fileSystemManager.addFile("failed File", 4));
    }

    @Test
    public void Test7() {
        Assert.assertTrue(fileSystemManager.addFile("File 1", 1));
        Assert.assertFalse(fileSystemManager.addFile("File 1", 1));
    }

    @Test
    public void Test8() {
        for (int i = 0; i < 15; i++) {
            Assert.assertTrue(fileSystemManager.addFile("File " + i, 4));
        }
        Assert.assertTrue(fileSystemManager.removeFile("File 0"));
        Assert.assertTrue(fileSystemManager.addFile("File 0", 7));
    }

    @Test
    public void Test9() {
        for (int i = 0; i < 16; i++) {
            Assert.assertTrue(fileSystemManager.addFile("File " + i, 4));
        }
        Assert.assertTrue(fileSystemManager.removeFile("File 15"));
        System.out.println(fileSystemManager.printINodes());
        System.out.println(fileSystemManager.printBitMap());
    }

    @Test
    public void Test10() {
        Assert.assertTrue(fileSystemManager.addFile("File", 64));
        System.out.println(fileSystemManager.printINodes());
        System.out.println(fileSystemManager.printBitMap());
        Assert.assertTrue(fileSystemManager.removeFile("File"));
    }
}
