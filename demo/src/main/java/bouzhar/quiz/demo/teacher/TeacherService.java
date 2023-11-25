package bouzhar.quiz.demo.teacher;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.teacher.Dto.TeacherDto;
import bouzhar.quiz.demo.teacher.Dto.TeacherResDto;
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

    /*
     *
     * Methods
     *
     * */

    // add new teacher
    @Override
    public TeacherResDto addNewTeacher(TeacherDto teacherDto) {
        if (teacherDto.getRegistrationDate() == null) teacherDto.setRegistrationDate(LocalDate.now());
        return
                modelMapper.map(teacherRepository.save(modelMapper.map(teacherDto, Teacher.class)), TeacherResDto.class);
    }

    // get all teacher
    @Override
    public TeacherResDto getTeacher(Long id) {
        return
                modelMapper.map(teacherRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("teacher with id " + id + " not found")
                ), TeacherResDto.class);
    }

    // get all teachers
    @Override
    public List<TeacherResDto> getAllTeachers() {
        return
                teacherRepository.findAll().stream()
                        .map(teacher -> modelMapper.map(teacher, TeacherResDto.class))
                        .toList();
    }

    // update teacher
    @Override
    public TeacherResDto updateTeacher(TeacherDto teacherDto) {
        teacherRepository.findById(teacherDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("teacher with id " + teacherDto.getId() + " not found")
        );

        return
                modelMapper.map(teacherRepository.save(modelMapper.map(teacherDto, Teacher.class)), TeacherResDto.class);
    }

    // delete teacher
    @Override
    public TeacherResDto deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("teacher with id " + id + " not found")
        );
        if (!teacher.getTests().isEmpty())
            throw new IllegalStateException("teacher with id " + id + " has tests, deleting it could lead to data loss");
        teacherRepository.deleteById(id);
        return
                modelMapper.map(teacher, TeacherResDto.class);
    }
}
