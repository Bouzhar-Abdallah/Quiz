## Api documentation
### Level
endpoint| method | payload | description
-|--------|-|-
/api/v1/level/addLevel| `POST` |`{description:string, maxPoints: int, minPoints: int}`| create a level
/api/v1/level/getLevel/{id}| `GET`  | `-` | find level by id
/api/v1/level/{id}| `PUT`  | `{description:string, maxPoints: int, minPoints: int}` | edit a level
/api/v1/level/{id}/questions | `GET` | `-` | get questions of a specific level
/api/v1/level/getLevels| `GET` | `-` | get all levels
/api/v1/level/{id}| `DELETE` | `-` | delete level
