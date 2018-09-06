package garg.ayush.quiz;

import android.support.annotation.NonNull;

import java.util.Comparator;

class Points implements Comparable<Points>{
    String email;
    int points;

    public Points(String email, int points) {
        this.email = email;
        this.points = points;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

   @Override
    public int compareTo(Points p) {
        int compare = ((int) p.getPoints());

        return compare - this.points;
    }
    public static Comparator<Points> emailcomparator = new Comparator<Points>() {

        public int compare(Points p1, Points p2) {
            String email1 = p1.getEmail().toString();
            String email2 = p2.getEmail().toString();

            //ascending order
            return email2.compareTo(email1);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};
    public static Comparator<Points> pointsComparator = new Comparator<Points>() {

        public int compare(Points p1, Points p2) {

            int no1 = p1.getPoints();
            int no2 = p2.getPoints();

            /*For ascending order*/
            return no2-no1;


            /*For descending order*/
            //rollno2-rollno1;
        }};
}
