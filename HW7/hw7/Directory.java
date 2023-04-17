package hw07;

 import java.time.LocalDateTime;
 import java.util.LinkedList;
 
 public class Directory extends FSElement{
     Directory parent;
 
     LinkedList<FSElement> FSlist = new LinkedList<FSElement>();
     LinkedList<Directory> DIRList = new LinkedList<Directory>();
     LinkedList<File> Filelist = new LinkedList<File>();
     LinkedList<Link> ProxyList = new LinkedList<Link>();
 
     public Directory(Directory parent, String name, int size,LocalDateTime ldt){
         super( parent, name, size, ldt);
         this.parent=parent;
     }
 
     public LinkedList<FSElement> getChildren(){
         return this.FSlist;
     }
 
     public void appendChild(FSElement child){
         FSlist.add(child);
 
         if(child.isDirectory()){
             DIRList.add((Directory) child);
         }
         else{
             if(child.isProxy()){
                 ProxyList.add((Link) child);
             }
             else{
                 Filelist.add((File) child);
             }
             
         }
     }
 
     public int countChildren(){
         return FSlist.size();
     }
 
     public LinkedList<Directory> getSubDirectories(){
         
         // for (int i = 0; i < this.DIRList.size(); i++) {
         //     System.out.println(this.DIRList.get(i));
         // }
         return this.DIRList;
     }
 
     public LinkedList<File> getFiles(){
         return this.Filelist;
     }
 
     // Iterate through all children and add their sizes, return the result
     public int getTotalSize(){
         int totalSize=0;
 
         //Adds size for all sub directories
         for (Directory childDirectory : DIRList) {
             totalSize+= childDirectory.getTotalSize();
         }
 
         //Adds size for all immediate child files within this directory
         for(File f : Filelist){
             totalSize+= f.getSize();
         }
         return totalSize;
     }
 
     public Directory getParentDirectory(){
         return this.parent;
     }
 
     public LinkedList<Link> getProxies(){
         return this.ProxyList;
     }
 
     @Override
     public boolean isDirectory() {
         return true;
     }
 
     @Override
     public boolean isProxy() {
         return  false;
     }
 }
