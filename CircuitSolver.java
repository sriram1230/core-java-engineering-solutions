import java.util.Scanner;

public class CircuitSolver {

    // Method to calculate the determinant of a 2x2 matrix
    public static double calculateDeterminant(double[][] matrix) {
        return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ECE Mesh/Nodal Analysis Circuit Solver (2x2) ===");
        System.out.println("Form: R11(I1) + R12(I2) = V1");
        System.out.println("      R21(I1) + R22(I2) = V2\n");

        // Main characteristic matrix (Resistances/Admittances)
        double[][] mainMatrix = new double[2][2];
        // Constants vector (Voltages/Current sources)
        double[] constants = new double[2];

        // Input gathering
        System.out.print("Enter R11: "); mainMatrix[0][0] = scanner.nextDouble();
        System.out.print("Enter R12: "); mainMatrix[0][1] = scanner.nextDouble();
        System.out.print("Enter V1: ");  constants[0] = scanner.nextDouble();

        System.out.print("Enter R21: "); mainMatrix[1][0] = scanner.nextDouble();
        System.out.print("Enter R22: "); mainMatrix[1][1] = scanner.nextDouble();
        System.out.print("Enter V2: ");  constants[1] = scanner.nextDouble();

        // Calculate Principal Determinant (D)
        double D = calculateDeterminant(mainMatrix);

        if (D == 0) {
            System.out.println("\nError: The determinant is zero. The equations cannot be solved (No unique solution).");
        } else {
            // Create matrices for Cramer's Rule
            double[][] d1Matrix = {{constants[0], mainMatrix[0][1]}, {constants[1], mainMatrix[1][1]}};
            double[][] d2Matrix = {{mainMatrix[0][0], constants[0]}, {mainMatrix[1][0], constants[1]}};

            // Calculate D1 and D2
            double D1 = calculateDeterminant(d1Matrix);
            double D2 = calculateDeterminant(d2Matrix);

            // Solve for unknowns (e.g., Currents I1 and I2)
            double unknown1 = D1 / D;
            double unknown2 = D2 / D;

            System.out.println("\n=== Solution ===");
            System.out.printf("Value 1 (e.g., I1 or V1): %.4f\n", unknown1);
            System.out.printf("Value 2 (e.g., I2 or V2): %.4f\n", unknown2);
        }
        scanner.close();
    }
}
