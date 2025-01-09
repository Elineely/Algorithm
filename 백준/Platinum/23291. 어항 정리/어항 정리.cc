#include <vector>
#include <queue>
#include <iostream>

#define MAX 101
using namespace std;

int n, k;
deque<int> container[MAX];

int dx[] = {0, -1};
int dy[] = {1, 0};

void addFish(){
	int minFish = 10001;
	vector<int> index;
	for (int i = 0; i < container[0].size(); ++i){
		if (container[0][i] < minFish){
			minFish = container[0][i];
			index.clear();
			index.push_back(i);
		}
		else if (container[0][i] == minFish){
			index.push_back(i);
		}
	}
	for (int idx : index){
		++container[0][idx];
	}
}

bool check(){
	int minFish = 10001;
	int maxFish = -1;
	for(int i = 0; i < container[0].size(); ++i){
		minFish = min(minFish, container[0][i]);
		maxFish = max(maxFish, container[0][i]);
	}
	return (maxFish - minFish <= k ? true : false);
}

bool canBuild(int w, int h){
	if (container[0].size() - w < h)
		return false;
	return true;
}

int buildContainer(){
	int width = 1;
	int height = 1;
	bool flag = false;

	while (true){
		if (canBuild(width, height) == false){
			break;
		}
		int tempW = width;
		for (int i = 0; i < width; ++i, --tempW){
			int idx = 0;
			for (int j = 0; j < height; ++j, ++idx)
				container[tempW].push_back(container[idx][i]);
		}

		for (int i = 0; i < height; ++i){
			for (int j = 0; j < width; ++j)
				container[i].pop_front();
		}

		if (flag == false){
			++height;
			flag = true;
		}
		else {
			++width;
			flag = false;
		}
	}
	return height;
}

void adjustFish(int height){
	deque<int> tempContainer[MAX];
	for(int i = 0; i < height; ++i)
		tempContainer[i] = container[i];

	for (int i = height - 1; i >= 0; --i){
		for (int j = 0; j < container[i].size(); ++j){
			int x = i;
			int y = j;
			int num = container[x][y];
			for (int s = 0; s < 2; ++s){
				int nx = x + dx[s];
				int ny = y + dy[s];
				if(nx >= 0 && ny < container[i].size()){
					int num2 = container[nx][ny];
					int diff = abs(num - num2) / 5;
					if (diff > 0){
						if (num > num2){
							tempContainer[x][y] -= diff;
							tempContainer[nx][ny] += diff;
						}
						else{
							tempContainer[nx][ny] -= diff;
							tempContainer[x][y] += diff;
						}
					}
				}
			}
		}
	}

	for (int i = 0; i < height; ++i)
		container[i] = tempContainer[i];
}

void buildContainer2(){
	int n = container[0].size();
	
	for (int i = 0; i < n / 2; ++i)
		container[1].push_front(container[0][i]);
	for (int i = 0; i < n / 2; ++i)
		container[0].pop_front();
	
	for (int i = 0; i < n / 4; ++i){
		container[2].push_front(container[1][i]);
		container[3].push_front(container[0][i]);
	}
	for (int i = 0; i < n / 4; ++i){
		container[0].pop_front();
		container[1].pop_front();
	}
}

void spreadContainer(int height){
	int width = container[height - 1].size();
	int size = container[0].size();

	for (int i = 0; i < width; ++i)
		for(int j = 0; j < height; ++j)
			container[0].push_back(container[j][i]);
	for (int i = 1; i < height; ++i)
		container[i].clear();

	for (int i = width; i < size; ++i)
		container[0].push_back(container[0][i]);
	for (int i = 0; i < size; ++i)
		container[0].pop_front();
}

int main(){
	cin >> n >> k;
	vector<int> before(n, 0);
	for(int i = 0; i < n; ++i){
		int temp;
		cin >> temp;
		container[0].push_back(temp);
	}
	int answer = 0;
	while(true){
		if(check() == true){
			cout << answer << endl;
			break ;
		}

		addFish();
		int height = buildContainer();
		adjustFish(height);
		spreadContainer(height);
		buildContainer2();
		adjustFish(4);
		spreadContainer(4);
		++answer;
	}
	return 0;
}