package nl.sparrow.cashflow.gui.menu;

import javafx.scene.control.TreeItem;

import java.util.List;
import java.util.function.Function;

public class TreeViewHelper
{
   /**
    * Syncs treeItem children with the mapped data from the datalist
    * @param treeItem the tree item to be synced
    * @param dataList data the treeitem needs to contain
    * @param mapper mapper to map the data from datalist to a string type
    * @param <T> type of data in the datalist
    */
   public static <T> void syncTreeItem(TreeItem<String> treeItem, List<T> dataList, Function<T, String> mapper){
      //Add any missing components
      for(T data: dataList){
         boolean isNewData = treeItem.getChildren().stream()
            .map(treeData -> treeData.getValue())
            .noneMatch(string -> string.equals(mapper.apply(data)));

         if(isNewData){
            treeItem.getChildren().add(new TreeItem<>(mapper.apply(data)));
         }
      }

      //remove any access components
      for(TreeItem<String> treeData: treeItem.getChildren()){
         boolean isDeprecatedData = dataList.stream()
            .map(data -> mapper.apply(data))
            .noneMatch(string -> string.equals(treeData.getValue()));

         if(isDeprecatedData){
            treeItem.getChildren().remove(treeData);
         }
      }
   }
}
