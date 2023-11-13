## Api documentation

### Level
endpoint| method | payload | description
-|--------|-|-
/api/v1/level/addLevel| `POST` |`{description:string, maxPoints: long, minPoints: long}`| create a level
/api/v1/level/{id}| `PUT`  | `{description:string, maxPoints: long, minPoints: long}` | edit a level
/api/v1/level/getLevels| `GET` | `-` | get all levels
/api/v1/level/getLevel/{id}| `GET`  | `-` | find level by id
/api/v1/level/{id}/questions | `GET` | `-` | get questions of a specific level
/api/v1/level/{id}| `DELETE` | `-` | delete level

### Subject
endpoint| method | payload | description
-|--------|-|-
/api/v1/subject/addSubject| `POST` |`{name: string,parent: {id: long}}`| create a subject
/api/v1/subject/{id}| `PUT`  | `{name: string,parent: {id: long}}` | edit a subject
/api/v1/subject/getSubjects| `GET` | `-` | get all subjects
/api/v1/subject/getSubject/{id}| `GET`  | `-` | find subject by id
/api/v1/subject/{id}/questions | `GET` | `-` | get questions of a specific subject
/api/v1/subject/{id}| `DELETE` | `-` | delete subject

	