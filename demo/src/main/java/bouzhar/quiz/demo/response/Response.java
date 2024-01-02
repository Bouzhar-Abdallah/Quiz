        package bouzhar.quiz.demo.response;

        import bouzhar.quiz.demo.assignment.Assignment;
        import bouzhar.quiz.demo.validation.Validation;
        import com.fasterxml.jackson.annotation.JsonIgnore;
        import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        @Entity
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public class Response {

            @EmbeddedId
            @JsonIgnore
            private ResponseId id;

            @MapsId("assignmentId")
            @ManyToOne
            private Assignment assignment;

            @ManyToOne(cascade = CascadeType.PERSIST)
            //@JoinColumn(name = "question_id", insertable = false, updatable = false)
            @JsonIgnore
            private Validation validation;
        }
