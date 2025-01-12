package com.madgalactic.SpringSecurityTraining;

public class Student {

        private int id;
        private String name;
        private int marks;

        public int getId() {
            return id;
        }

        public Student(int id, String name, int marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getMarks() {
            return marks;
        }

        public void setMarks(int marks) {
            this.marks = marks;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", marks=" + marks +
                    '}';
        }
}

