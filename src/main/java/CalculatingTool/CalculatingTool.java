package CalculatingTool;

import java.util.Scanner;

public class CalculatingTool {
    protected String toolName;
    protected Boolean isOn;
    protected double toolCost;
    protected double toolWeight;

    public CalculatingTool(String name, double cost, double weight)
    {
        this.toolName = name;
        this.toolCost = cost;
        this.toolWeight = weight;
    }

    CalculatingTool()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter tool name (default = 'name') ");
        toolName = scanner.nextLine();
        System.out.println("Enter tool cost (default = '1') ");
        toolCost = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter tool weight (default = '1') ");
        toolWeight = Double.parseDouble(scanner.nextLine());
    }

    public String getToolName() {
        return toolName;
    }

    public double getToolCost() {
        return toolCost;
    }

    public Boolean getOn() {
        return isOn;
    }

    public double getToolWeight() {
        return toolWeight;
    }
}
