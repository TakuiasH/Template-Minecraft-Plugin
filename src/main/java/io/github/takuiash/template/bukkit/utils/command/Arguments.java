package io.github.takuiash.template.bukkit.utils.command;

public class Arguments {

	private String[] args;
	private int index = 0;
	
	public Arguments(String[] args) {
		this.args = args;
	}
	
	/**
	 * Returns the length of the arguments
	 */
	public int length() {
		return args.length;
	}
	
	/**
	 * Save the index value
	 * 
	 * @param index Value to be kept
	 */
	public Arguments i(int index) {
		return index(index);
	}
	
	/**
	 * Save the index value
	 * 
	 * @param index Value to be kept
	 */
	public Arguments index(int index) {
		this.index = (index-1);
		return this;
	}
	
	/**
	 * Returns the value of the argument at a given index
	 * 
	 * @param index Index of value
	 */
	public String get(int index) {
		return index(index).get();
	}
	
	/**
	 * Returns the value of the argument at a stored index
	 */
	public String get() {
		if(args.length <= index)
			return null;
		
		return args[index];
	}
	
	public String[] getAll(int startIndex) {
		if(startIndex > args.length)
			throw new IndexOutOfBoundsException("startIndex can't be bigger than args length");
		
		startIndex = startIndex < 1 ? 0 : startIndex-1;
		String[] v = new String[args.length - startIndex];
		int argIndex = startIndex;
		for(int i = 0; i < (args.length - startIndex); i++) {
			v[i] = args[argIndex];
			argIndex++;
		}
		return v;
	}
	
	/**
	 * Returns true if the indexed value is the same as the passed argument
	 * 
	 * @param argument Argument to check
	 */
	public boolean equalsIgnoreCase(String argument) {
		String value = get();
		
		if(value == null)
			return false;
		
		return value.equalsIgnoreCase(argument);
	}
	
	/**
	 * Returns true if the indexed value is the same as the passed argument (case insensitive)
	 * 
	 * @param argument Argument to check
	 */
	public boolean equals(String argument) {
		String value = get();
		
		if(value == null)
			return false;
		
		return value.equals(argument);
	}
	
}
