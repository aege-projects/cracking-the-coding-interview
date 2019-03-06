public class BuildOrder {
	enum State {
		UNVISITED,
		VISITING,
		VISITED
	}

	class Project {
		private String name;
		private State state = UNVISITED;
		private List<Project> dependencies = new ArrayList<> ();

		public Project(String n) { name = n; }

		public void addEdge(Project project) {
			dependencies.add(project);
		}

		public boolean isInCycle() {
			state = VISITING;

			for (Project project : dependencies) {
				if (project.state == VISITING) {
					return true;
				}

				if (project.state == UNVISITED) {
					if (project.isInCycle()) {
						return true;
					}
				}
			}

			project.state = VISITED;
			return false;
		}

		public State getState() {
			return state;
		}

		public void setState(State state) {
			this.state = state;
		}

		public void dfs(Stack<Project> sorted) {
			state = VISITED;
			for (Project project : dependencies) {
				if (project.state == UNVISITED) {
					project.dfs(sorted);
				}
			}

			sorted.push(this);
		}
	}

	class Graph {
		private Set<Project> projects = new HashSet<> ();
		
		public void addEdge(Project from, Project to) {
			if (!projects.contains(from)) {
				projects.add(from);
			}

			if (!projects.contains(to)) {
				projects.add(to);
			}

			from.addEdge(to);
		}
		
		public boolean containsCycle() {
			for (Project project : projects) {
				if (project.getState() == UNVISITED) {
					if (project.hasCycle()) {
						return true;
					}
				}
			}

			return false;
		}

		
		public ArrayList<Project> topoSort() {
			if (containsCycle()) {
				return null;
			}
		
			for (Project project : projects) {
				project.setState(UNVISITED);
			}
			
			Stack<Project> sorted = new Stack<> ();
			for (Project project : projects) {
				if (project.getState() == UNVISITED) {
					project.dfs(sorted);
				}
			}

			ArrayList<Project> ans = new ArrayList<> ();
			while (!sorted.isEmpty()) {
				ans.add(sorted.pop());
			}

			return ans;
		}

	}
}
