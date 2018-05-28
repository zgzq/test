package test;

public class TreeTest {

	/**
	 * Node�ڵ���
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
	 * ����������
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
	   *ǰ�����
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
	   * ����
	   * @param val
	   */
	  public void insert(int val){
		  Node p = new Node();  
		  p.data = val;  
		  
		//���������Ϊ�գ�ֱ�ӽ�Ҫ�����ֵ��Ϊ��  
		  if (this.root == null) {  
		   this.root = p;  
		  }   
		  
		//�����������Ϊ�գ��ж�Ҫ����������ʱp��ָ����ֵ�Ĵ�С����������������ô�Ͳ嵽�Һ��ӣ���֮���뵽���ӡ�  
		  else {  
		   Node q = this.root;  
		   while (true) {  
		  
		    if (val < q.data) {  
		     if (q.left == null) {  
		  
		   //˫��ָ��  
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
		  
		     //˫��ָ��  
		      q.right = p;  
		      p.parent=q;  
		      break;  
		     }  
		     else {  
		      q = q.right;  
		     }  
		  
		    }   
		    else {  
		     System.out.println("�Ѵ���");  
		    }  
		   }  
		  }  
	  }
	  /**
	   * ����
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
		 * ɾ��
		 * @param key
		 * @throws Exception
		 */
		
		public void delete(int key) throws Exception {
	         Node pNode = searchNode(key);
	          if (pNode == null) {
	               throw new Exception("�����в�����Ҫɾ��������ڵ㣡");
	          }
	          System.out.println(pNode);

	          delete(pNode);
	     }

	     //���������������һ���ݹ�ķ����������� Ҫɾ���Ľڵ�������ӽڵ㶼�ǿգ�
	     //����Ҫɾ��������ڵ�ĺ����ڵ�Ҳ�������������
	     private void delete(Node pNode) throws Exception {
	          // ��һ�����:ɾ��û���ӽڵ�Ľڵ�
	          if (pNode.left == null && pNode.right == null) {
	               if (pNode == root) {// ����Ǹ��ڵ㣬��ô��ɾ��������
	                    root = null;
	               } else if (pNode == pNode.parent.left) {
	                    // �������ڵ��Ǹ��ڵ����ڵ㣬�򽫸��ڵ����ڵ���Ϊ��
	                    pNode.parent.left = null;
	               } else if (pNode == pNode.parent.right) {
	                    // �������ڵ��Ǹ��ڵ���ҽڵ㣬�򽫸��ڵ���ҽڵ���Ϊ��
	                    pNode.parent.right = null;
	               }
	          }

	          // �ڶ�������� ��ɾ����һ���ӽڵ�Ľڵ㣩
	          // ���Ҫɾ���Ľڵ�ֻ���ҽڵ�
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
	          // ���Ҫɾ���Ľڵ�ֻ����ڵ�
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

	          // ���Ҫɾ���Ľڵ��������ӽڵ㣬�������ӽڵ㶼�ǿ�
	          // ��������Ҫɾ���Ľڵ�ĺ����ڵ����Ҫɾ���Ľڵ㣬����ɾ�������ڵ㣨ɾ�������ڵ��ʱ��ͬ���ĵݹ������
	          // ��ʵ������Ҫ�õ������Ҳ�ͻᷢ�����Σ��������ڵ㲻���ټ����ݹ��ɾ����һ�������ڵ���
	          // ��Ϊ��Ҫɾ���Ľڵ�ĺ����ڵ�϶��� Ҫɾ�����Ǹ��ڵ������������С�ؼ��֣��������С�ؼ��ֿ϶���������ڵ�
	          // ���ԣ���ɾ�������ڵ��ʱ��϶������õ��� �����ڵ㶼�ǿյ��ж� �����������ӽڵ㣬�϶�������һ���ҽڵ㡣
	          if (pNode.left != null && pNode.right != null) {
	               // ���ҳ������ڵ�
	               Node successorNode = successor(pNode);
	               if (pNode == root) {
	                    root.data = successorNode.data;
	               } else {
	                    pNode.data = successorNode.data;//��ֵ���������ڵ��ֵ����Ҫɾ�����Ǹ��ڵ�
	               }
	               delete(successorNode);//�ݹ��ɾ�������ڵ�
	          }
	     }
		
	     public Node successor(Node node) throws Exception {
	          if (node == null) {
	               throw new Exception("����Ϊ������");
	          }
	          // ������������ǣ��˽ڵ��Ƿ���������
	          // ������ڵ���������������£���ô����������С�ؼ��ֽڵ��������ڵ�ĺ����ڵ�
	          if (node.right != null) {
	               return minElemNode(node.right);
	          }

	          // ������ڵ�û���������������,�� node.rchild == null
	          // �������ڵ�ĸ��ڵ�������� �� ����ڵ���ͬ�Ļ�����ô��˵��������ڵ���Ǻ����ڵ���
	          // �ѵ����ﻹ��Ҫ��������if����𣿲���Ҫ�ˣ�������һ��whileѭ���Ϳ�����
	          Node parentNode = node.parent;
	          while (parentNode != null && parentNode.right == node) {
	               node = parentNode;
	               parentNode = parentNode.parent;
	          }
	          return parentNode;
	     }
	     public Node minElemNode(Node node) throws Exception {
	          if (node == null) {
	               throw new Exception("����Ϊ������");
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
