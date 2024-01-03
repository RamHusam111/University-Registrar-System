
# University Academic Management System

## Overview
This project is a Java-based system designed to manage various aspects of academic operations within a university or college. It includes classes for handling courses, faculty, students, semesters, specializations, teachers, and weekly meetings.

## Components
- `WeeklyMeeting`: Manages course schedules, including meeting times and rooms. It is a description of time and place that can be used by a certain course.
- `Course`: Manages all aspects of a course, it holds record of course Faculty, creditHours, courses weeklyMeetings, and the max course capacity.
            Upon registration the course will also hold the teacher and students enrolled in that course.
- `Faculty`: Represents a faculty of a certain field. The Faculty holds the student who's their specialization falls under the fields of that faculty (Example: a Faculty of Science will hold all science major students).
- `Person`: A base  abstract class for entities: students and teachers.
- `RegistrarDriver`: The main driver class for the application.
- `Semester`: Handles semester-related information. It includes that main function of the system - the register method.
- `Specialization`: Manages academic specializations likr majors, minor and the stutdents that are enrolled in that sepcialization, under a Faculty.
- `Student`: Manages student-specific details like courses and GPA.
- `Teacher`: Handles teacher-specific information.

## Setup Instructions
1. Ensure Java is installed on your system.
2. Compile each Java file using a Java compiler (e.g., `javac ClassName.java`).
3. Run the `RegistrarDriver` class to start the application (e.g., `java RegistrarDriver`).

## Usage
Once the application is running, you can perform various academic management tasks such as enrolling students in courses, assigning teachers, and scheduling classes. Interaction is typically done through the `RegistrarDriver` class, which coordinates activities across different components.

## Notes
- This system is designed for educational purposes and may require modifications for use in a real-world setting.
- Further documentation is provided within each Java file.

## Author
Object Orienters.


