package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Repository.CourseRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
   private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void testToVerifyCourse(){
        CourseRequest courseRequest = new CourseRequest("physic","phy101");
        Course course = new Course(1L,"physic","phy101");
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course course1 = courseService.addNewCourse(courseRequest);

        assertNotNull(course1);
        assertEquals("physic", course1.getCourseName());
        assertEquals("phy101", course1.getCourseCode());

        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testViewCourse(){
        Course course = new Course(1L,"chemistry","chm101");
        Course course1 = new Course(2L,"physics","phy101");
        Course course2 = new Course(3L , "biology","bio101");
        List<Course> courses = Arrays.asList(course, course1,course2);

        when(courseRepository.findAll()).thenReturn(courses);
        List<Course> result = courseService.getStudentCourse();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("chemistry", result.get(0).getCourseName());
        assertEquals("physics", result.get(1).getCourseName());
        assertEquals(1L, result.get(0).getCourseId());
        assertEquals("phy101", result.get(1).getCourseCode());

        verify(courseRepository, times(1)).findAll();
    }


}