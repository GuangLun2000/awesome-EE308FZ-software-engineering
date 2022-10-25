## EE308FZ Lab2
Hi 👋🏻 This is the blog of EE308FZ Lab2 built by Hanlin Cai.

![请添加图片描述](https://img-blog.csdnimg.cn/6340ffe160c54bcc9dc0e9255516b7c3.jpeg#pic_center)
|EE308FZ Lab2| [My Github Repo]|
|--|--|
| For Requirement|[Requirement]|
|My Code Specification|[Specification]|
|The main Class for my blog| [here]|

### (1) PSP Form for Lab2
|Personal Software Process Stages  | Estimated Time/minutes	 |Completed Time/minutes|
|--|--|--|
| Analysis and Planing| 30| 30|
|Program Development|60|40|
|Program Review|10|10|
|Performance Test|20|15|
|Blog Writing|40|40|
|Github Preparation|20|30|
|**Total**|**180**|**165**|

---
### (2-3) Description of idea to solve this problem
![Design](https://img-blog.csdnimg.cn/f16699af02e6427fb83af191ba6101db.jpeg#pic_center)

```cpp
Cpp Design:
1. Extract the keywords if and else and consider else-if as one keyword
2. At the same time, also extract the "level" of each of them like this:
3. Then, we can process the if-else and if-else if-else with all the levels
4. Traverse the same level at a time.
```
---
### (4-6) Code description & Unit test & Optimization
#### 1. main
```cpp
int main()
{
	string path;
	vector<string> code;
	int level;
	read(path, level);
	readFile(path, code);
	preprocess(code);
	solve(code, level);
	return 0;
}
```
#### 2. read & preprocess
```cpp
void read(string &path, int &level)
{
	cout << "Please type in the path if code file: ";
	puts("");
	cout << "The path should be written like: /Users/lancecai/Documents/GitHub/EE308FZ/Record-for-LAB/Lab1/ctest.txt" << endl;
	cin >> path;
	cout << "Please select the level of completion (1 to 4): ";
	cin >> level;
	if (level <= 0 || level > 5)
	{
		throw "Unexpected level number!";
	}
}

void readFile(string &path, vector<string> &code)
{
	ifstream file(path);
	string temp;
	while (file >> temp)
	{
		code.push_back(temp);
	}
	// for (int i = 0; i < code.size(); i++)
	// {
	// 	cout << code[i] << endl;
	// }
	//cout << code << endl;
}


bool isNotLetter(char x)
{
	return !((x >= 65) && (x <= 90) || (x >= 97) && (x <= 122) || (x == '{') || (x == '}'));
}

void preprocess(vector<string> &code)
{
	for (int i = 0; i < code.size(); i++)
	{
		string temp = code[i];
		temp.erase(std::remove_if(temp.begin(), temp.end(), isNotLetter),
		temp.end());
		code[i] = temp;
	}
}
```
#### 3. count keywords

```cpp
const set<string> keywords = {"auto", "break", "case", "char", "const",
							  "continue", "default", "do", "double", "else",
							  "enum", "extern", "float", "for", "goto", "if",
							  "int", "long", "register", "return", "short",
							  "signed", "sizeof", "static", "struct", "switch",
							  "typedef", "union", "unsigned", "void",
							  "volatile", "while"};


/* 
Return the total num of keywords in a code file
the path directed to.
*/
int countKeywords(vector<string> &code)
{
	int total_num = 0;
	for (int i = 0; i < code.size(); i++)
	{
		if (keywords.find(code[i]) != keywords.end())
		{
			total_num++;
		}
	}
	return total_num;
}
```
#### 4. count switch-case
```cpp
/*
Print the number of "switch case" structures, and output 
the number of "case" corresponding to each group
*/
void countSwitchAndCase(vector<string> &code)
{
	int switch_num = 0;
	int case_temp_num = 0;
	vector<int> case_num;
	for (int i = 0; i < code.size(); i++)
	{
		if (code[i] == "switch")
		{
			switch_num++;
			case_temp_num = 0;
			for (int j = i + 1; j < code.size(); j++)
			{
				if (code[j] == "switch")
				{
					break;
				}
				else
				{
					if (code[j] == "case")
					{
						case_temp_num++;
					}
				}
			}
			case_num.push_back(case_temp_num);
		}
	}
	cout << "switch num: " << switch_num << endl;
	cout << "case num: ";
	for (int i = 0; i < case_num.size(); i++)
	{
		cout << case_num[i] << " ";
	}
	puts("");
}
```
#### 5. count if-else (2 types)
1. if-else
2. if-else & if-else

```cpp
deque<string> extractIfElse(vector<string> &code)
{
	deque<string> if_else;
	for (int i = 0; i < code.size(); i++)
	{
		bool flag = (code[i] == "if" || code[i] == "else" || code[i] == "{" || code[i] == "}");
		if (flag)
		{
			if_else.push_back(code[i]);
		}
	}
	//if_else.pop_back();
	//if_else.pop_front();
	return if_else;
}


vector<key_level_pair> extractKeyLevel(vector<string> &code)
{
	int flag = 1;
	deque<string> if_else = extractIfElse(code);
	vector<key_level_pair> key_level_vec;
	for (int i = 0; i < if_else.size(); i++)
	{
		if (if_else[i] == "if" && if_else[i + 1] == "{" && if_else[i - 1] != "else" && if_else[i + 2] != "}")
		{
			key_level_pair temp = {"if", flag};
			key_level_vec.push_back(temp);
			flag++;
			// int j = i;
			// while (!(if_else[j]  == "else" && if_else[j + 3] != "}") )
			// {

			// }
		}
		if (if_else[i] == "if" && if_else[i + 1] == "{" && if_else[i - 1] != "else" && if_else[i + 2] == "}")
		{
			key_level_pair temp = {"if", flag};
			key_level_vec.push_back(temp);
		}
		if (if_else[i] == "else" && if_else[i + 3] != "}" && if_else[i + 1] != "if" && if_else[i + 1] == "{")
		{
			key_level_pair temp = {"else", flag};
			key_level_vec.push_back(temp);
			flag++;
		}
		if (if_else[i] == "else" && if_else[i + 3] == "}" && if_else[i + 1] != "if")
		{
			key_level_pair temp = {"else", flag};
			key_level_vec.push_back(temp);
			flag -= 1;
		}
		if (if_else[i] == "else" && if_else[i + 1] == "if" && if_else[i + 3] == "}" && if_else[i + 2] == "{")
		{
			key_level_vec.push_back({"else if", flag});
		}
		if (if_else[i] == "else" && if_else[i + 1] == "if" && if_else[i + 3] != "}" && if_else[i - 1] != "if")
		{
			key_level_vec.push_back({"else if", flag});
			flag++;
		}
	}
	return key_level_vec;
}


/*
Perfectly handling multiple nesting
*/
int countIfElse(vector<string> &code)
{
	int num = 0;
	vector<key_level_pair> key_level_vec;
	key_level_vec = extractKeyLevel(code);
	for (int i = 0; i < key_level_vec.size(); i++)
	{
		if (key_level_vec[i].first == "if")
		{
			for (int j = i; j < key_level_vec.size(); j++)
			{
				if (key_level_vec[j].first == "else if")
				{
					break;
				}

				if (key_level_vec[j].first == "else" && key_level_vec[i].second == key_level_vec[j].second)
				{
					num++;
				}
			}
		}
	}

	// for (int i = 0; i < key_level_vec.size(); i++)
	// {
	// 	cout << key_level_vec[i].first << " " << key_level_vec[i].second << endl;
	// }

	return num;
}


int countIfElseIfElse(vector<string> &code)
{
	int num = 0;
	vector<key_level_pair> key_level_vec = extractKeyLevel(code);
	bool has_else_if = false;
	for (int i = 0; i < key_level_vec.size(); i++)
	{
		if (key_level_vec[i].first == "if")
		{
			for (int j = i + 1; j < key_level_vec.size(); j++)
			{
				if (key_level_vec[j].first == "if" && key_level_vec[i].second == key_level_vec[j].second)
				{
					break;
				}
				if (key_level_vec[j].first == "else if" && key_level_vec[i].second == key_level_vec[j].second)
				{
					has_else_if = true;
				}
				if (key_level_vec[j].first == "else" && key_level_vec[i].second == key_level_vec[j].second)
				{
					if (has_else_if)
					{
						num++;
					}
				}
			}
			has_else_if = false;
		}
	}
	return num;
}
```
### Test Union Screenshots (1 to 4)
![请添加图片描述](https://img-blog.csdnimg.cn/41f8c014184e428bb6865064388a0323.png)
```cpp
Please type in the path if code file: 
The path should be written like: /Users/lancecai/Desktop/Lab-2022W/Lab-EE304-Software/Record/Lab2/ctest.txt
/Users/lancecai/Desktop/Lab-2022W/Lab-EE304-Software/Record/Lab2/ctest.txt
Please select the level of completion (1 to 4): 5
Case 1: Count keywords
total num: 35
Case 2
switch num: 2
case num: 3 2 
Case 3
if-else num: 2
Case 4
if-elseif-else num: 2
```
### Performance Analysis

```cpp
int main()
{
	clock_t start, finish;
	double duration;
	cout << "--------------------------------"<< endl;
	start = clock();
	string path = "/Users/lancecai/Documents/GitHub/EE308FZ/Record-for-LAB/Lab1/ctest.txt";
	vector<string> code;
	int level1 = 1;
	int level2 = 2;
	int level3 = 3;
	int level4 = 4;
	//read(path, level);
	readFile(path, code);
	preprocess(code);
	solve(code, level1);
	solve(code, level2);
	solve(code, level3);
	solve(code, level4);
	finish = clock();
	cout << "--------------------------------"<< endl;
	duration = (double)(finish - start) / CLOCKS_PER_SEC;
	cout << "Time = ";
	printf("%f seconds\n", duration);
	return 0;
}
```

---
### (7) Summarize for Lab2
On my machine (Apple M1-chip 8GB), the time consumption is around:  `Time = 0.000081 seconds`. And the `Time complexity` is no more than $O(n^2)$, which is acceptable.

#### What did I learn in this Lab2
1. Revise every thing of C++
2. Learn how to use Git
3. Learn how to plan and design the program before typing in mess.
4. Learn how to Unit & Performance Testing.
5. Clam down and just do it!

---
### Statement for this blog![请添加图片描述](https://img-blog.csdnimg.cn/6340ffe160c54bcc9dc0e9255516b7c3.jpeg#pic_center =500x300)
| My EE308FZ Main Class| [Here]|
|--|--|
| The Link of requirement for this assignment2|[Requirement]|
| The aim of this assignment | File Read and Count Keyword|
| MU Student ID | 20122161
|FZU Student ID|832002117|
|My Email|hanlin.cai@ieee.org|

[My Github Repo]:https://github.com/GuangLun2000/awesome-EE308FZ-software-engineering/tree/main/Record-for-LAB/Lab2
[here]:https://blog.csdn.net/weixin_51100018/category_12066029.html?spm=1001.2014.3001.5482
[Requirement]:https://github.com/GuangLun2000/awesome-EE308FZ-software-engineering/tree/main/Requirement-for-LAB/Lab2
[Specification]:https://github.com/GuangLun2000/awesome-EE308FZ-software-engineering/blob/main/my-code-specifications/Cpp_Code_Specifications.md
[here]:https://blog.csdn.net/weixin_51100018/category_12066029.html
