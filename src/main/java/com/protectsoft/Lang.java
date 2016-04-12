package com.protectsoft;

public enum Lang {
	
	JAVA {
		@Override
		public String toString() {
			return "java";
		}
	},
	JAVASCRIPT {
		@Override
		public String toString() {
			return "javascript";
		}
	},
	C {
		@Override
		public String toString() {
			return "c";
		}
	},
	PHP {
		@Override
		public String toString() {
			return "php";
		}
	}
	//and more to add later
	

}
