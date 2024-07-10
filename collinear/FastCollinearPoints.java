import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private List<LineSegment> lineSegments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {

        if (points == null) throw new IllegalArgumentException();

        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        checkValidity(sortedPoints);

        int length = points.length;


        for (int p = 0; p < length; p++) {
            Point origin = sortedPoints[p];
            Point[] pointsBySlope = sortedPoints.clone();
            Arrays.sort(pointsBySlope, origin.slopeOrder());

            int count = 1;
            int j = 1;
            while (j < length - 2) {
                while (j < length - 1 && checkSlopesEqual(origin, pointsBySlope[j],
                                                          pointsBySlope[j + 1])) {
                    count++;
                    j++;
                }
                if (count >= 3)
                    addSegment(pointsBySlope, j - count + 1, j);
                j++;
                count = 1;
            }


        }
        // for (int p = 0; p < length; p++) {
        //
        //     Point origin = sortedPoints[p];
        //
        //     Point[] pointsBySlope = sortedPoints.clone();
        //     Arrays.sort(pointsBySlope, points[p].slopeOrder());
        //     // count of consecutive points with slopes wrt origin equal
        //     int count = 1;
        //     for (int q = 1; q < length; q++) {
        //         // current point slope vs previous point slope wrt origin
        //         if (Double.compare(origin.slopeTo(pointsBySlope[q]),
        //                            origin.slopeTo(pointsBySlope[q - 1])) == 0) {
        //             count++;
        //             // if end of array reached and at least 3 points slopes wrt are equal then at least 4 collinear points exit
        //             if (q == length - 1 && count >= 3)
        //                 addSegment(origin, pointsBySlope, q - count + 1, q);
        //         }
        //         // if the next point's slope != prev. point's slope wrt
        //         else {
        //             // if have at least 3 points + origin collinear then try to add as segment
        //             if (count >= 3) addSegment(origin, pointsBySlope, q - count, q - 1);
        //             // if don't have at least 3 points then reset counter and keep looking
        //             count = 1;
        //         }
        //     }
        // }
    }     // finds all line segments containing 4 or more points

    private boolean checkSlopesEqual(Point origin, Point q1, Point q2) {
        return Double.compare(origin.slopeTo(q1), origin.slopeTo(q2)) == 0;
    }

    private void checkValidity(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            if (i < points.length - 1 && points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException();
        }
    }

    // adds a line segment iff origin is the lowest point in the point the collection passed to it
    // this avoids adding duplicate line segments
    private void addSegment(Point[] pointsBySlope, int start, int end) {
        Point[] collinearPoints = new Point[end - start + 2];
        Point origin = pointsBySlope[0];
        collinearPoints[0] = origin;

        for (int i = 0; i < collinearPoints.length - 1; i++)
            collinearPoints[i + 1] = pointsBySlope[start + i];

        Arrays.sort(collinearPoints);

        if (origin.compareTo(collinearPoints[0]) == 0)
            lineSegments.add(new LineSegment(origin, collinearPoints[collinearPoints.length - 1]));
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[numberOfSegments()]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        // BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
