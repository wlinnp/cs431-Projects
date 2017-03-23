package edu.cpp.cs431.p1.Process;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import edu.cpp.cs431.p1.Misc.Generator;

/**
 * @author Wai Phyo
 * CS-431   |   Project-1
 * <p></p>
 * Simulating Process Table
 */
public class ProcessTableEngineDetails implements ProcessTableEngine {
    private String rootUser = "root";
    private Map<Integer, Process> processTable = new HashMap<Integer, Process>();
    private Process processInCPU;
    private Generator generator = new Generator();

    public ProcessTableEngineDetails() throws CloneNotSupportedException {
        init();
    }

    /**
     * Load default process to the CPU and add to the table.
     */
    private void init() throws CloneNotSupportedException {
        processInCPU = new Process(1,
                "init",
                "root",
                0,
                generator.getRandomInteger(),
                generator.getRandomInteger(),
                generator.getRandomInteger(),
                generator.getRandomInteger(),
                generator.getRandomInteger(),
                generator.getRandomInteger());
        processTable.put(processInCPU.getProcessID(), processInCPU.getClone());
    }

    /**
     * duplicate process in cpu with 'ready' status
     * <p></p>
     * save the new process in table.
     */
    @Override
    public void forkProcess() {
        if (processInCPU == null) {
            System.out.println("CPU is idle, nothing to fork.");
            return;
        }
        int newProcessID = getNewProcessID();
        processTable.put(newProcessID, (new Process((newProcessID),
                processInCPU.getProgramName(),
                processInCPU.getUser(),
                1,
                processInCPU.getPc(),
                processInCPU.getSp(),
                processInCPU.getR0(),
                processInCPU.getR1(),
                processInCPU.getR2(),
                processInCPU.getR3())));
    }

    /**
     * Start with a random number.
     * <p></p>
     * Increment it till the number is not in the table
     * <p></p>
     * @return new Process ID
     */
    private int getNewProcessID() {
        int potentialID = generator.getRandomPositiveInteger();
        while (processTable.get(potentialID) != null) {
            potentialID++;
        }
        return potentialID;
    }

    /**
     * Check process exists
     * <p></p>
     * Check if user can kill it
     * <p></p>
     * Kill it
     * <p></p>
     * Check it is running process.
     * <p></p>
     * If so load it with new process.
     * <p></p>
     * @param killedProcessID process ID to be removed
     */
    @Override
    public void killProcess(final int killedProcessID) throws CloneNotSupportedException {
        Process killingProcess = findProcess(killedProcessID);
        if (!validateKillingProcess(killingProcess)) {
            System.out.println("Cannot find the process or User has no permission to kill the process.");
            return;
        }
        processTable.remove(killedProcessID);

        if (processInCPU.getProcessID() == killingProcess.getProcessID()) {
            pushRandomProcessToCPU();
        }
    }

    private Process findProcess(final int processID) {
        return processTable.get(processID);
    }

    /**
     * If Process is null, invalid
     * <p></p>
     * If user is not root and user is not owner of process, invalid
     * <p></p>
     * @param killingProcess process to be killed
     * @return boolean
     */
    private boolean validateKillingProcess(final Process killingProcess) {
        return killingProcess != null && (processInCPU.getUser().equals(rootUser) || processInCPU.getUser().equals(killingProcess.getUser()));
    }

    /**
     * If Process is null, invalid
     * <p></p>
     * If user is not root and user is not owner of process, invalid
     * <p></p>
     * @param newUser user for the new process
     * @return boolean
     */
    private boolean validateUserAccessToProcess(final String newUser) {
        return processInCPU != null && (processInCPU.getUser().equals(rootUser) ||processInCPU.getUser().equals(newUser));
    }

    /**
     * Check there is a process to run in the table.
     * <p></p>
     * If so, update the process status.
     */
    private void pushRandomProcessToCPU() throws CloneNotSupportedException {
        Process newProcess = getRandomProcess(convertToList());
        if (newProcess == null) {
            System.out.println("No 'ready' process for CPU");
            return;
        }
        newProcess.setStatus(ProcessStatus.RUNNING.getStatus());
        processInCPU = newProcess.getClone();
    }

    /**
     * Randomly get one process from the list of all process with status 'ready'
     * <p></p>
     * @param allValidProcesses Array List of all process with 'ready' status
     * @return selected process to run
     */
    private Process getRandomProcess(List<Process> allValidProcesses) {
        if (allValidProcesses.isEmpty()) {
            return null;
        }
        if (allValidProcesses.size() == 1) {
            return allValidProcesses.get(0);
        }
        return (allValidProcesses.get(generator.getRandomIntegerWithBounds(0, allValidProcesses.size() - 1)));
    }

    /**
     * Loop process table and add processes with 'ready' status to a new list.
     * <p></p>
     * @return Array List of all process with 'ready' status
     */
    private List<Process> convertToList() {
        List<Process> readyProcesses = new ArrayList<Process>();
        for(Map.Entry<Integer, Process> each : processTable.entrySet()) {
            if (each.getValue().getStatus() == ProcessStatus.READY.getStatus()) {
                readyProcesses.add(each.getValue());
            }
        }
        return readyProcesses;
    }

    @Override
    public void print() {
        System.out.println(printCPU());
        System.out.println(printProcessTable());
    }

    /**
     * Print CPU content
     * <p></p>
     * Print idle if there is nothing
     * <p></p>
     * @return CPU content
     */
    private String printCPU() {
        StringBuilder result = new StringBuilder("CPU:");
        if (processInCPU == null) {
            result.append("idle")
                    .append(Generator.NEXT_LINE);
            return result.toString();
        }
        result.append(Generator.NEXT_LINE)
                .append("PC = ")
                .append(generator.printHexString(processInCPU.getPc()))
                .append(Generator.TAB)
                .append("SP = ")
                .append(generator.printHexString(processInCPU.getSp()))
                .append(Generator.NEXT_LINE)
                .append("R0 = ")
                .append(generator.printHexString(processInCPU.getR0()))
                .append(Generator.TAB)
                .append("R1 = ")
                .append(generator.printHexString(processInCPU.getR1()))
                .append(Generator.NEXT_LINE)
                .append("R2 = ")
                .append(generator.printHexString(processInCPU.getR2()))
                .append(Generator.TAB)
                .append("R3 = ")
                .append(generator.printHexString(processInCPU.getR3()))
                .append(Generator.NEXT_LINE)
                .append(Generator.NEXT_LINE);
        return result.toString();
    }

    /**
     * Loop Process table and print all
     * @return process table content
     */
    private String printProcessTable() {
        StringBuilder result = new StringBuilder("Process Table:");
        result.append(Generator.NEXT_LINE)
                .append("PID")
                .append(Generator.TAB)
                .append("Program")
                .append(Generator.TAB)
                .append("User")
                .append(Generator.TAB)
                .append("Status")
                .append(Generator.TAB)
                .append("PC")
                .append(Generator.TAB)
                .append("SP")
                .append(Generator.TAB)
                .append("R0")
                .append(Generator.TAB)
                .append("R1")
                .append(Generator.TAB)
                .append("R2")
                .append(Generator.TAB)
                .append("R3")
                .append(Generator.NEXT_LINE);

        for (Integer key : processTable.keySet()) {
            Process eachProcess = processTable.get(key);
            result.append(key)
                    .append(Generator.TAB)
                    .append(eachProcess.getProgramName())
                    .append(Generator.TAB)
                    .append(eachProcess.getUser())
                    .append(Generator.TAB)
                    .append(eachProcess.getStatus())
                    .append(Generator.TAB)
                    .append(generator.printHexString(eachProcess.getPc()))
                    .append(Generator.TAB)
                    .append(generator.printHexString(eachProcess.getSp()))
                    .append(Generator.TAB)
                    .append(generator.printHexString(eachProcess.getR0()))
                    .append(Generator.TAB)
                    .append(generator.printHexString(eachProcess.getR1()))
                    .append(Generator.TAB)
                    .append(generator.printHexString(eachProcess.getR2()))
                    .append(Generator.TAB)
                    .append(generator.printHexString(eachProcess.getR3()))
                    .append(Generator.NEXT_LINE);
        }
        return result.toString();
    }

    /**
     * remove the process in CPU.
     * <p></p>
     * add new process to CPU
     */
    @Override
    public void exitProcess() throws CloneNotSupportedException {
        if (processInCPU == null) {
            System.out.println("no process in CPU to exit.");
            return;
        }
        processTable.remove(processInCPU.getProcessID());
        processInCPU = null;
        pushRandomProcessToCPU();
    }

    /**
     * Update CPU process status to blocked.
     * <p></p>
     * Add new process to CPU
     */
    @Override
    public void blockProcess() throws CloneNotSupportedException {
        if (processInCPU == null) {
            System.out.println("no process in CPU to block.");
            return;
        }
        processInCPU.setStatus(ProcessStatus.BLOCKED.getStatus());
        processTable.put(processInCPU.getProcessID(), processInCPU.getClone());
        processInCPU = null;
        pushRandomProcessToCPU();
    }

    /**
     * Find the process. and unblock it.
     * @param processID unblocking process id
     */
    @Override
    public void unblockProcess(final int processID) throws CloneNotSupportedException {
        Process unblockingProcess = findProcess(processID);
        if (unblockingProcess == null) {
            System.out.println("Process not found");
            return;
        }
        processTable.get(processID).setStatus(ProcessStatus.READY.getStatus());
        if (processInCPU == null) {
            pushRandomProcessToCPU();
        }
    }

    /**
     * Switch current CPU process with another one in the table.
     */
    @Override
    public void yieldProcess() throws CloneNotSupportedException {
        if (processInCPU == null) {
            System.out.println("no process in CPU to yield.");
            return;
        }
        Process newProcessInCPU = getRandomProcess(convertToList());
        if (newProcessInCPU != null) {
            processTable.get(processInCPU.getProcessID()).setStatus(ProcessStatus.READY.getStatus());
            newProcessInCPU.setStatus(ProcessStatus.RUNNING.getStatus());
            processInCPU = newProcessInCPU.getClone();
        }
    }

    /**
     * Switch new process name and user for the process in CPU
     *
     * @param newProgramName new process name
     * @param newUser new process user
     */
    @Override
    public void execveProcess(final String newProgramName, final String newUser) throws CloneNotSupportedException {
        if (!validateUserAccessToProcess(newUser)) {
            System.out.println("Cannot find the process or User has no permission to kill the process.");
            return;
        }
        processInCPU.setUser(newUser);
        processInCPU.setProgramName(newProgramName);
        simulateCopyFromStack();
        processTable.put(processInCPU.getProcessID(), processInCPU.getClone());
    }

    /**
     * new process should have different register content.
     * <p></p>
     * simulating by filling all registers with random values.
     */
    private void simulateCopyFromStack() {
        processInCPU.setPc(generator.getRandomInteger());
        processInCPU.setSp(generator.getRandomInteger());
        processInCPU.setR0(generator.getRandomInteger());
        processInCPU.setR1(generator.getRandomInteger());
        processInCPU.setR2(generator.getRandomInteger());
        processInCPU.setR3(generator.getRandomInteger());
    }
}
