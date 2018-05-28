package test;

public class TreeTest {

	/**
	 * Node节点类
	 * @author Administrator
	 *
	 */
	private class Node{  
        public Node left;  
        public Node right;  
        public Node parent;
        public int data;  
        public Node(){}
        public Node(int data){  
             
            this.data = data;  
        }  
        public Node(Node left,Node right,Node parent,int data){
        	this.data = data;
        	this.left = left;
        	this.right = right;
        	this.parent = parent;
        }
        
    }  
	private Node root;
	public TreeTest(){
		root = null;
	}
	/**
	 * 创建二叉树
	 * @param node
	 * @param data
	 */
	  public void buildTree(Node node,int data){  
	        if(root == null){  
	            Node p = new Node(data);  
	            root = p;
	        }else{  
	            if(data < node.data){  
	                if(node.left == null){  
	                    Node p= new Node(data); 
	                    node.left = p;
	                    p.parent=node;
	                    //p = node.left;
	                }else{  
	                    buildTree(node.left,data);  
	                }  
	            }else{  
	                if(node.right == null){  
	                    Node p = new Node(data); 
	                    node.right = p;
	                    p.parent=node;
	                    //p = node.right;
	                   
	                }else{  
	                    buildTree(node.right,data);  
	                }  
	            }  
	        }  
	    } 
	  /**
	   *前序遍历
	   * @param node
	   */
	  public void preOrder(Node node){  
	        if(node != null){  
	            System.out.println(node.data);  
	            preOrder(node.left);  
	            preOrder(node.right);  
	        }  
	    } 
	  /**
	   * 插入
	   * @param val
	   */
	  public void insert(int val){
		  Node p = new Node();  
		  p.data = val;  
		  
		//如果二叉树为空，直接将要插入的值作为根  
		  if (this.root == null) {  
		   this.root = p;  
		  }   
		  
		//如果二叉树不为空，判断要插入的数与此时p所指结点的值的大小，如果插入的数大，那么就插到右孩子，反之插入到左孩子。  
		  else {  
		   Node q = this.root;  
		   while (true) {  
		  
		    if (val < q.data) {  
		     if (q.left == null) {  
		  
		   //双向指向  
		      q.left = p;  
		      p.parent=q;  
		      break;  
		     }   
		     else {  
		      q = q.left;  
		     }  
		    }  
		    else if (val > q.data) {  
		     if (q.right == null) {  
		  
		     //双向指向  
		      q.right = p;  
		      p.parent=q;  
		      break;  
		     }  
		     else {  
		      q = q.right;  
		     }  
		  
		    }   
		    else {  
		     System.out.println("已存在");  
		    }  
		   }  
		  }  
	  }
	  /**
	   * 查找
	   * @param data
	   * @return
	   */
	  public Node searchNode(int data){  
		    return searchNode(this.root,data);  
		}  
		  
		 
		private Node searchNode(Node node,int data){  
		    Node searchResutlNode = null;  
		    if(node == null){  
		        return  null;  
		    }  
		      
		    if(node.data == data){  
		        return node;  
		    }  
		      
		    if(node.left != null){  
		        searchResutlNode = searchNode(node.left,data);  
		        if( searchResutlNode != null ) return searchResutlNode;  
		    }  
		      
		    if(node.right != null){  
		        searchResutlNode = searchNode(node.right,data);  
		        if( searchResutlNode != null ) return searchResutlNode;  
		    }  
		    return null;  
		}
	  
		
		/**
		 * 删除
		 * @param key
		 * @throws Exception
		 */
		
		public void delete(int key) throws Exception {
	         Node pNode = searchNode(key);
	          if (pNode == null) {
	               throw new Exception("此树中不存在要删除的这个节点！");
	          }
	          System.out.println(pNode);

	          delete(pNode);
	     }

	     //这个方法可以算是一个递归的方法，适用于 要删除的节点的两个子节点都非空，
	     //并且要删除的这个节点的后续节点也有子树的情况下
	     private void delete(Node pNode) throws Exception {
	          // 第一种情况:删除没有子节点的节点
	          if (pNode.left == null && pNode.right == null) {
	               if (pNode == root) {// 如果是根节点，那么就删除整棵树
	                    root = null;
	               } else if (pNode == pNode.parent.left) {
	                    // 如果这个节点是父节点的左节点，则将父节点的左节点设为空
	                    pNode.parent.left = null;
	               } else if (pNode == pNode.parent.right) {
	                    // 如果这个节点是父节点的右节点，则将父节点的右节点设为空
	                    pNode.parent.right = null;
	               }
	          }

	          // 第二种情况： （删除有一个子节点的节点）
	          // 如果要删除的节点只有右节点
	          if (pNode.left == null && pNode.right != null) {
	               if (pNode == root) {
	                    root = pNode.right;
	               } /*else if (pNode == pNode.parent.left) {
	                    pNode.parent.left = pNode.right;
	                    pNode.right.parent = pNode.parent;
	               } */else if (pNode == pNode.parent.right) {
	                    pNode.parent.right = pNode.right;
	                    pNode.right.parent = pNode.parent;
	               }
	          }
	          // 如果要删除的节点只有左节点
	          if (pNode.left != null && pNode.right == null) {
	               if (pNode == root) {
	                    root = pNode.left;
	               } else if (pNode == pNode.parent.left) {
	                    pNode.parent.left = pNode.left;
	                    pNode.left.parent = pNode.parent;
	               }/* else if (pNode == pNode.parent.right) {
	                    pNode.parent.right = pNode.left;
	                    pNode.left.parent = pNode.parent;
	               }*/
	          }

	          // 如果要删除的节点有两个子节点，即左右子节点都非空
	          // 方法是用要删除的节点的后续节点代替要删除的节点，并且删除后续节点（删除后续节点的时候同样的递归操作）
	          // 其实，这里要用到的最多也就会发生两次，即后续节点不会再继续递归的删除下一个后续节点了
	          // 因为，要删除的节点的后续节点肯定是 要删除的那个节点的右子树的最小关键字，而这个最小关键字肯定不会有左节点
	          // 所以，在删除后续节点的时候肯定不会用到（ 两个节点都非空的判断 ），如有有子节点，肯定就是有一个右节点。
	          if (pNode.left != null && pNode.right != null) {
	               // 先找出后续节点
	               Node successorNode = successor(pNode);
	               if (pNode == root) {
	                    root.data = successorNode.data;
	               } else {
	                    pNode.data = successorNode.data;//赋值，将后续节点的值赋给要删除的那个节点
	               }
	               delete(successorNode);//递归的删除后续节点
	          }
	     }
		
	     public Node successor(Node node) throws Exception {
	          if (node == null) {
	               throw new Exception("此树为空树！");
	          }
	          // 分两种情况考虑，此节点是否有右子树
	          // 当这个节点有右子树的情况下，那么右子树的最小关键字节点就是这个节点的后续节点
	          if (node.right != null) {
	               return minElemNode(node.right);
	          }

	          // 当这个节点没有右子树的情况下,即 node.rchild == null
	          // 如果这个节点的父节点的左子树 与 这个节点相同的话，那么就说明这个父节点就是后续节点了
	          // 难道这里还需要进行两次if语句吗？不需要了，这里用一个while循环就可以了
	          Node parentNode = node.parent;
	          while (parentNode != null && parentNode.right == node) {
	               node = parentNode;
	               parentNode = parentNode.parent;
	          }
	          return parentNode;
	     }
	     public Node minElemNode(Node node) throws Exception {
	          if (node == null) {
	               throw new Exception("此树为空树！");
	          }
	          Node pNode = node;
	          while (pNode.left != null) {
	               pNode = pNode.left;
	          }
	          return pNode;
	     }
		
	  
	  
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		/*Integer j = 2;
		int i = j.compareTo(3);
		System.out.println(i);*/
		
		 int[] a = {2,4,12,45,21,6,111};  
		/* Arrays.sort(a);
		 for(int i = 0; i < a.length; i++){
			 System.out.println(a[i]);
		 }
		 int j = a[(a.length+1)/2];
		 System.out.println(j);*/
	        TreeTest bTree = new TreeTest();  
	        for (int i = 0; i < a.length; i++) {  
	            bTree.buildTree(bTree .root, a[i]);  
	        }  
	        //bTree.preOrder(bTree.root);  
	       bTree.insert(38);
	       bTree.delete(111);
	       bTree.preOrder(bTree.root);  
	       //System.out.println(bTree.searchNode(3));
		
	}

}
