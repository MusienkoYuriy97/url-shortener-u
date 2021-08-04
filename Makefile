build:
	docker build -t 97musienko/url .
	docker login -u 97musienko -p 04612e6b-d0b4-44ca-83aa-e3a9fd19e096
	docker push 97musienko/url