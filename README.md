# openjdk-jol (Java Object Layout)

## Shallow Size: the Course Class
### ClassLayout.parseClass(Course.class).toPrintable()

Course object internals:

| OFFSET | SIZE | TYPE DESCRIPTION              | VALUE |
|--------|------|-------------------------------|-------|
| 0      | 12   | (object header)               | N/A   |
| 12     | 4    | java.lang.String Course.name  |    N/A   |

- Instance size: 16 bytes
- Space losses: 0 bytes internal + 0 bytes external = 0 bytes total


## Shallow Size: the Professor Class
### ClassLayout.parseClass(Professor.class).toPrintable())
Professor object internals:

| OFFSET | SIZE | TYPE DESCRIPTION             | VALUE |
|--------|------|------------------------------|-------|
| 0      | 12   | (object header)              | N/A   |
| 12     | 4    | int Professor.level    |    N/A   |
| 16     | 8    | double Professor.lastEvaluation    |    N/A   |
| 24     | 1    | boolean Professor.tenured   |    N/A   |
| 25     | 3    | (alignment/padding gap)   |    N/A   |
| 28     | 4    | java.lang.String Professor.name   |    N/A   |
| 32     | 4    | java.util.List Professor.courses    |    N/A   |
| 26     | 4    | java.time.LocalDate Professor.birthDay     |    N/A   |

- Instance size: 40 bytes
- Space losses: 3 bytes internal + 0 bytes external = 3 bytes total


## Shallow Size: an Instance
### VM.current().sizeOf(course)/ VM.current().sizeOf(ds)

- The shallow size of 'course; is: 16
- The shallow size of 'ds' is: 24

## Deep Size: an Instance
### deep size of the Course instance is equal to the shallow size of the Course instance itself plus the deep size of that particular String instance

### Deep size of the String instance ds
### ClassLayout.parseInstance(ds).toPrintable()

| OFFSET | SIZE | TYPE DESCRIPTION            | VALUE                                                             |
|--------|------|-----------------------------|-------------------------------------------------------------------|
| 0      | 4    | (object header)             | 05 00 00 00 (00000101 00000000 00000000 00000000) (5)             |
| 4      | 4    | (object header)    | 00 00 00 00 (00000000 00000000 00000000 00000000) (0)             |
| 8      | 4    | (object header)    | 08 18 00 00 (00001000 00011000 00000000 00000000) (6152)          |
| 12     | 4    | byte[] String.value   | [68, 97, 116, 97, 32, 83, 116, 114, 117, 99, 116, 117, 114, 101, 115] |
| 16     | 4    | int String.hash  | 0                                                                 |
| 20     | 1    | byte String.coder  | 0                                                                 |
| 21     | 3    | (loss due to the next object alignment)   |                                                                   |

- Instance size: 24 bytes
- Space losses: 0 bytes internal + 3 bytes external = 3 bytes total

### The shallow size of this String instance is 24 bytes, which include the 4 bytes of cached hash code, 4 bytes of byte[] reference, and other typical object overhead.
### To see the actual size of the byte[], we can parse its class layout, too:
[B object internals:

| OFFSET | SIZE | TYPE DESCRIPTION           | VALUE                                                        |
|--------|------|----------------------------|--------------------------------------------------------------|
| 0      | 4    | (object header)            | 01 00 00 00 (00000001 00000000 00000000 00000000) (1)        |
| 4      | 4    | (object header)    | 00 00 00 00 (00000000 00000000 00000000 00000000) (0)        |
| 8      | 4    | (object header)    | 20 08 00 00 (00100000 00001000 00000000 00000000) (2080)     |
| 12     | 4    | (object header)    | 0f 00 00 00 (00001111 00000000 00000000 00000000) (15) |
| 16     | 15   | byte [B.<elements>  | N/A                                                             |
| 31     | 1    | (loss due to the next object alignment)  |                                                              |

- Instance size: 32 bytes
- Space losses: 0 bytes internal + 1 bytes external = 1 bytes total

### So, we have 16 bytes for the Course instance, 24 bytes for the String instance, and finally 32 bytes for the byte[]. In total, the deep size of that Course instance is 72 bytes.

## Object Graph Layout
### GraphLayout.parseInstance(course).toFootprint()
Course@5a4041ccd footprint:

| COUNT | AVG | SUM | DESCRIPTION                                                        |
|-------|----|-----|--------------------------------------------------------------|
| 1     | 16 | 16  | Course        |
| 1     | 32 | 32  | [B        |
| 1     | 24 | 24  | java.lang.String     |
| 3     |    | 72  | (total) |


### GraphLayout.parseInstance(professor).toFootprint()
Professor@58a90037d footprint:

| COUNT | AVG | SUM | DESCRIPTION                                            |
|-------|-----|-----|--------------------------------------------------------|
| 1     | 16  | 16  | Course                                                 |
| 1     | 40  | 40  | Professor                                              |
| 1     | 28  | 56  | [B(Byte array for Both strings Data Structures + Test) |
| 3     | 56  | 56  | [Ljava.lang.Object; List<Object>                       |
| 3     | 24  | 48  | java.lang.String for both strings                      |
| 3     | 24  | 24  | java.time.LocalDate                                             |
| 3     | 24  | 24  | java.util.ArrayList                                             |
| 3     | 24  | 264 | (total)                                                |