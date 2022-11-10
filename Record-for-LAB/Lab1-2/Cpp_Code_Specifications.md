# **Code Specifications (Lance Cai)**

**Heavily based on: [Google C++ Style Guide]([Google 开源项目风格指南——中文版 — Google 开源项目风格指南 (zh-google-styleguide.readthedocs.io)](https://zh-google-styleguide.readthedocs.io/en/latest/)) from Google and [CS312 SML Style Guide]([CS312 SML Style Guide (cornell.edu)](http://www.cs.cornell.edu/courses/cs312/2001SP/style.html)) from Cornell University and the style of @wang-zhuoran.** 

## 1.  Indentation

**No Tab Characters! **

Intend 4 spaces: Most lines that indent code should only indent by four spaces more than the previous line of code. (OR Set one tab as 4 spaces in the editor)

## 2.  Variable naming

### ①  Using meaningful names

Variable names should be meaningful and readable as well as should describe what they are for. Do not use a single letter except one special case: ```i``` and ``` j ``` or any other letters in a loop.

### ②  Case and Naming Conventions

All the variables(include function parameters) should be lower case. Different words in a single variable should be connected with an **underscore**, for example, ```my_variable```, ```num_points```, ```years```.

## 3.  Maximum number of characters per line

80 Columns Limit: No line of code can have more than 80 columns.

## 4.  Maximum number of  function lines

40 Lines Limit: Not necessary but strongly recommended.

## 5.  Function and class naming

### ①  Function naming

The name of function use **camel case naming**. The name of get and set methods should match the corresponding variable name.

*Examples*:

```C++
void setVariable(int variable);
int getName();
void StartPCR();
```

### ②  Class naming

Start with upper letter, each word is capitalized without underscores.

*Examples*:

```c++
class Student{...}
struct StudentName{...}
enum UrlErrors{...}
```

## 6.  Constant

All the letters should be upper cases. Using underscore to separate different words.

```constexpr``` is safer than ```const```, but both of them are acceptable. 

*Examples*:

```c++
const int NUM = 10;
const double HOUSE_PRICE = 128.9;
```

## 7.  Blank line rule

Always put a blank line after the local declarations in a function. Don't put more than one blank line in a row. A single blank line at a time is enough to break up the flow of code properly.

## 8. Comments/Annotation rules

① Comments Go Above the Code They Reference: For consistency sake (and to avoid issues with the 80 column limit), always place comments above code, e.g.,

```C++
/* This is a well-placed comment. */
#define AN_IMPORTANT_NUMBER 42
```

② Avoid Useless Comments: Comments that merely repeat the code it references or state the obvious are a travesty to programmers. Comments should state the invariants, the non-obvious, or any references that have more information about the code.

③ Avoid Over-commenting: Incredibly long comments are not very useful. Long comments should only appear at the top of a file.

④ Every function should include its docstring, including the return value and its purpose, etc.

## 9.  Space before and after operator

There is always a space before and after the assignment operator. Other binary operators always have spaces before and after. There is no immediate space inside the parentheses. No spaces between arguments and unary operators.

```C++
// There is always a space before and after the assignment operator.
x = 0;

// Other binary operators always have spaces before and after.
// There is no immediate space inside the parentheses.
v = w * x + y / z;

v = w * (x + z);

// No spaces between arguments and unary operators.
x = -5;
++x;
if (x && !y)
  ...
```

## 10. Braces

Always use the Allman style braces, i.e. push the opening brace onto its own line:

```c++
namespace N
{
    class C
    {
        void foo()
        {
            int x;
        }
    };
}
```

