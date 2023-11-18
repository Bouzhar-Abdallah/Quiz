package bouzhar.quiz.demo.Teacher;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherService implements TeacherServiceSpecification {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<TeacherDto> addNewTeacher(TeacherDto teacherDto) {
        if (teacherDto.getRegestrationDate() == null)teacherDto.setRegestrationDate(LocalDate.now());
        return ResponseEntity.ok(
                modelMapper.map(teacherRepository.save(modelMapper.map(teacherDto,Teacher.class)), TeacherDto.class)
        );
    }

    @Override
    public ResponseEntity<TeacherDto> updateTeacher(TeacherDto teacherDto) {
        return null;
    }

    @Override
    public ResponseEntity<TeacherDto> deleteTeacher(Long Id) {
        return null;
    }

    @Override
    public ResponseEntity<TeacherDto> getTeacher(Long Id) {
        return null;
    }

    @Override
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return ResponseEntity.ok(
          teacherRepository.findAll().stream()
                  .map(teacher -> modelMapper.map(teacher, TeacherDto.class))
                  .toList()
        );
    }
}
