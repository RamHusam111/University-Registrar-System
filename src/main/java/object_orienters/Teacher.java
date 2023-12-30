package object_orienters;


public class Teacher extends Person {

  private Specialization specialization;

  public Teacher(String name, Specialization specialization) {
    super(Role.TEACHER, name);
    this.specialization = specialization;
    specialization.getTeachers().add(this);
    specialization.getFaculty().getTeachers().add(this);

  }

  public Specialization getSpecialization() {
    return specialization;
  }

  @Override
  public String toString() {
    return super.toString() + "\nSpecialization: "
        + this.getSpecialization();
  }
}
