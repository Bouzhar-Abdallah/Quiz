## Api documentation

### Level
| endpoint                     | method   | payload                                                  | description                       |
|------------------------------|----------|----------------------------------------------------------|-----------------------------------|
| /api/v1/level                | `POST`   | `{description:string, maxPoints: long, minPoints: long}` | create a level                    |
| /api/v1/level                | `PUT`    | `{description:string, maxPoints: long, minPoints: long}` | edit a level                      |
| /api/v1/level                | `GET`    | `-`                                                      | get all levels                    |
| /api/v1/level/{id}           | `GET`    | `-`                                                      | find level by id                  |
| /api/v1/level/{id}/questions | `GET`    | `-`                                                      | get questions of a specific level |
| /api/v1/level/{id}           | `DELETE` | `-`                                                      | delete level                      |

### Subject
| endpoint                        | method   | payload                             | description                         |
|---------------------------------|----------|-------------------------------------|-------------------------------------|
| /api/v1/subject                 | `POST`   | `{name: string,parent: {id: long}}` | create a subject                    |
| /api/v1/subject                 | `PUT`    | `{name: string,parent: {id: long}}` | edit a subject                      |
| /api/v1/subject                 | `GET`    | `-`                                 | get all subjects                    |
| /api/v1/subject/getSubject/{id} | `GET`    | `-`                                 | find subject by id                  |
| /api/v1/subject/{id}/questions  | `GET`    | `-`                                 | get questions of a specific subject |
| /api/v1/subject/{id}            | `DELETE` | `-`                                 | delete subject                      |

### Answer
| endpoint            | method   | payload            | description       |
|---------------------|----------|--------------------|-------------------|
| /api/v1/answer      | `POST`   | `{answer: string}` | create a answer   |
| /api/v1/answer      | `PUT`    | `{answer: string}` | edit a answer     |
| /api/v1/answer      | `GET`    | `-`                | get all answers   |
| /api/v1/answer/{id} | `GET`    | `-`                | find answer by id |
| /api/v1/answer/{id} | `DELETE` | `-`                | delete answer     |

### Question
| endpoint              | method   | payload                                                                                                                                                                          | description         |
|-----------------------|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------|
| /api/v1/question      | `POST`   | `{answersCount:int, correctAnsqersCount: int, text: string, type: string, totalScore: double, subject_id: int, level_id: int, medias: Array{name:string, type: string}}`         | create a question   |
| /api/v1/question      | `PUT`    | `{id:int ,answersCount:int, correctAnsqersCount: int, text: string, type: string, totalScore: double, subject_id: int, level_id: int, medias: Array{name:string, type: string}}` | edit a question     |
| /api/v1/question      | `GET`    | `-`                                                                                                                                                                              | get all questions   |
| /api/v1/question/{id} | `GET`    | `-`                                                                                                                                                                              | find question by id |
| /api/v1/question/{id} | `DELETE` | `-`                                                                                                                                                                              | delete question     |

### Student
| endpoint             | method   | payload                                                                | description        |
|----------------------|----------|------------------------------------------------------------------------|--------------------|
| /api/v1/student      | `POST`   | `{firstName:string, lastName: string, email : string, adress: string}` | create a student   |
| /api/v1/student      | `PUT`    | `{firstName:string, lastName: string, email : string, adress: string}` | edit a student     |
| /api/v1/student      | `GET`    | `-`                                                                    | get all students   |
| /api/v1/student/{id} | `GET`    | `-`                                                                    | find student by id |
| /api/v1/student/{id} | `DELETE` | `-`                                                                    | delete student     |

### Teacher
| endpoint             | method   | payload                                                                                                              | description        |
|----------------------|----------|----------------------------------------------------------------------------------------------------------------------|--------------------|
| /api/v1/teacher      | `POST`   | `{ firstName: string, lastName: string, email: string, adress: string, regestrationDate: date, speciality: string }` | create a teacher   |
| /api/v1/teacher      | `PUT`    | `{ firstName: string, lastName: string, email: string, adress: string, regestrationDate: date, speciality: string }` | edit a teacher     |
| /api/v1/teacher      | `GET`    | `-`                                                                                                                  | get all teachers   |
| /api/v1/teacher/{id} | `GET`    | `-`                                                                                                                  | find teacher by id |
| /api/v1/teacher/{id} | `DELETE` | `-`                                                                                                                  | delete teacher     |

### Test
| endpoint                 | method   | payload                                                                                                                                                                    | description             |
|--------------------------|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------|
| /api/v1/test             | `POST`   | `{ duration: int, title: string, successScore: reel, showResults: boolean, showResps: boolean, maxAttempts: int, remarks: string, instructions: string, teacher_id: int }` | create a test           |
| /api/v1/test             | `PUT`    | `{ duration: int, title: string, successScore: reel, showResults: boolean, showResps: boolean, maxAttempts: int, remarks: string, instructions: string, teacher_id: int }` | edit a test             |
| /api/v1/test             | `GET`    | `-`                                                                                                                                                                        | get all tests           |
| /api/v1/test/{id}        | `GET`    | `-`                                                                                                                                                                        | find test by id         |
| /api/v1/test/{id}        | `DELETE` | `-`                                                                                                                                                                        | delete test             |
| /api/v1/test/addQuestion | `Post`   | `{ duration : int, question_id: int, test_id : int }`                                                                                                                      | attach question to test |

### Assignment
| endpoint                               | method   | payload                                                                                                                    | description                          |
|----------------------------------------|----------|----------------------------------------------------------------------------------------------------------------------------|--------------------------------------|
| /api/v1/assignement                    | `POST`   | `{ startDate: date, endDate: date, chance: int, result: bolean, obtainedScore: reel, test_id: int, student_id: int }`      | create a assignement                 |
| /api/v1/assignement                    | `PUT`    | `{ startDate: date, endDate: date, chance: int, result: bolean, obtainedScore: reel, test_id: int, student_id: int }`      | edit a assignement                   |
| /api/v1/assignement                    | `GET`    | `-`                                                                                                                        | get all assignements                 |
| /api/v1/assignement/{id}               | `GET`    | `-`                                                                                                                        | find assignement by id               |
| /api/v1/assignement/{id}               | `DELETE` | `-`                                                                                                                        | delete assignement                   |
| /api/v1/assignement/addAssignementList | `POST`   | `{ startDate: date, endDate: date, chance: int, result: bolean, obtainedScore: reel, test_id: int, Array{student_id:int}}` | add assignements for a students list |

### Validation
| endpoint                                    | method   | payload                                                                 | description         |
|---------------------------------------------|----------|-------------------------------------------------------------------------|---------------------|
| /api/v1/validation                          | `POST`   | `{ question_id: int, answer_id: int, isCorrect: boolean, score: reel }` | create a validation |
| /api/v1/validation                          | `PUT`    | `{ question_id: int, answer_id: int, isCorrect: boolean, score: reel }` | edit a validation   |
| /api/v1/validation/{questionid}/{answer_id} | `DELETE` | `-`                                                                     | delete validation   |

