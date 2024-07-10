import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] lineSegments;
    private int numberOfLines;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Argument is null");

        lineSegments = new LineSegment[1];
        numberOfLines = 0;

        // Create a copy of points to avoid mutating the original array
        Point[] pointsCopy = points.clone();

        // Check for null entries in the array
        for (Point point : pointsCopy) {
            if (point == null) throw new IllegalArgumentException("Array contains null entries");
        }

        // Sort the copied array
        Arrays.sort(pointsCopy);
        int length = pointsCopy.length;

        // Check validity of points
        checkValidity(pointsCopy);

        // Checking every combination of 4 points and add if all three slopes to other points are equal
        for (int p = 0; p < length - 3; p++) {
            for (int q = p + 1; q < length - 2; q++) {
                double slopePQ = pointsCopy[p].slopeTo(pointsCopy[q]);
                for (int r = q + 1; r < length - 1; r++) {
                    double slopePR = pointsCopy[p].slopeTo(pointsCopy[r]);
                    if (Double.compare(slopePQ, slopePR) == 0) {
                        for (int s = r + 1; s < length; s++) {
                            double slopePS = pointsCopy[p].slopeTo(pointsCopy[s]);
                            if (Double.compare(slopePQ, slopePS) == 0) {
                                addLine(new LineSegment(pointsCopy[p], pointsCopy[s]));
                            }
                        }
                    }
                }
            }
        }
    }

    private void checkValidity(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("Array contains null entries");
            if (i < points.length - 1 && points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Array contains duplicate entries");
        }
    }

    private void resize(int newLength) {
        LineSegment[] temp = new LineSegment[newLength];
        for (int i = 0; i < numberOfLines; i++)
            temp[i] = lineSegments[i];
        lineSegments = temp;
    }

    private void addLine(LineSegment line) {
        if (numberOfLines == lineSegments.length) resize(lineSegments.length * 2);
        lineSegments[numberOfLines++] = line;
    }

    public int numberOfSegments() {
        return numberOfLines;
    }

    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[numberOfLines];
        for (int i = 0; i < numberOfLines; i++) temp[i] = lineSegments[i];
        return temp;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
