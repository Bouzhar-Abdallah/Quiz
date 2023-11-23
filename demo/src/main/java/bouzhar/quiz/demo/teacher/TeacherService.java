package bouzhar.quiz.demo.teacher;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.teacher.Dto.TeacherDto;
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
        teacherRepository.findById(teacherDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("teacher with id "+ teacherDto.getId() +" not found")
        );

        return ResponseEntity.ok(
                modelMapper.map(teacherRepository.save(modelMapper.map(teacherDto,Teacher.class)), TeacherDto.class)
        );
    }

    @Override
    public ResponseEntity<TeacherDto> deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("teacher with id "+ id +" not found")
        );
        teacherRepository.deleteById(id);
        return ResponseEntity.ok(
                modelMapper.map(teacher,TeacherDto.class)
        );
    }

    @Override
    public ResponseEntity<TeacherDto> getTeacher(Long id) {
        return ResponseEntity.ok(
          modelMapper.map(teacherRepository.findById(id).orElseThrow(
                  () -> new ResourceNotFoundException("teacher with id "+ id +" not found")
          ), TeacherDto.class)
        );
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
