package edu.cpp.cs431.p1.Process;

/**
 * @author Wai Phyo
 * CS-431   |   Project-1
 * <p></p>
 * Get-Set methods for Process
 */
public class Process implements Cloneable {
    private int processID;
    private String programName;
    private String user;
    private int status;
    private int pc;
    private int sp;
    private int r0;
    private int r1;
    private int r2;
    private int r3;

    public Process(int processID, String programName, String user, int status, int pc, int sp, int r0, int r1, int r2, int r3) {
        this.processID = processID;
        this.programName = programName;
        this.user = user;
        this.status = status;
        this.pc = pc;
        this.sp = sp;
        this.r0 = r0;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
    }

    public int getProcessID() {
        return processID;
    }

    public String getProgramName() {
        return programName;
    }

    public String getUser() {
        return user;
    }

    public int getStatus() {
        return status;
    }

    public int getPc() {
        return pc;
    }

    public int getSp() {
        return sp;
    }

    public int getR0() {
        return r0;
    }

    public int getR1() {
        return r1;
    }

    public int getR2() {
        return r2;
    }

    public int getR3() {
        return r3;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public void setR0(int r0) {
        this.r0 = r0;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public void setR3(int r3) {
        this.r3 = r3;
    }

    public Process getClone() throws CloneNotSupportedException {
        return (Process)clone();
    }
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
