package [[${packages}]];

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.util.List;
[# th:each="item:${imports}"]
import [[${item}]];  
[/]

@RestController
@RequestMapping("/[[${className2}]]")
public class [[${className}]]Controller {
    @Autowired
    private [[${className}]]Service [[${className2}]]Service;

    /**
     * 新增
     */
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody [[${className}]] [[${className2}]]){
        int res=[[${className2}]]Service.insert([[${className2}]]);
        if(res==1){
            return ResultGenerator.genSuccessResult();
        }else{
        	return ResultGenerator.genFailResult(null);
        }
    }

    /**
     * 删除
     */
    @GetMapping("/delete/{ids}")
    public Result<String> delete(@PathVariable("ids") String ids){
    	if(null==ids||ids.equals("")){
    		return ResultGenerator.genFailResult(null);
    	}
        String str[]=ids.split(",");
        for(String s:str){
            [[${className2}]]Service.deleteByPrimaryKey(Integer.valueOf(s));
        }
        return ResultGenerator.genSuccessResult();
    }
    
    /**
     * 更新
     */
    @PostMapping("/update")
    public Result<String> update(@RequestBody [[${className}]] [[${className2}]]){
    	int res=[[${className2}]]Service.updateByPrimaryKey([[${className2}]]);
    	if(res==1){
    		return ResultGenerator.genSuccessResult();
    	}else{
    		return ResultGenerator.genFailResult(null);
    	}
    }
    
    /**
     * 主键查询
     */
    @GetMapping("/info/{id}")
    public Result<?> selectById(@PathVariable("id") int id){
    	[[${className}]] [[${className2}]] = [[${className2}]]Service.selectByPrimaryKey(id);
    	if(null==[[${className2}]]){
    		return ResultGenerator.genFailResult(null);
    	}else{
    		return ResultGenerator.genSuccessResult([[${className2}]]);
    	}
    }
    
    /**
     * 条件和排序的分页查询
     */
    @PostMapping("/search")
    public void search(
    		@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            [[${className}]] [[${className2}]],HttpServletResponse response){
    	//设置分页参数
    	Map<String, Object> map = new HashMap<String, Object>();
        if (page != null && rows != null) {
            PageBean pageBean = new PageBean(Integer.parseInt(page),
                    Integer.parseInt(rows));
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        
        [# th:if="${isSearch}!=null"]
        //设置查询条件参数
        if([[${className2}]]!=null){
        	[# th:each="item:${searchParams}"]
        	map.put("[[${item2}]]",[[${className2}]].get[[${item}]]());
        	[/]
        }
        [/]
        
        //设置排序参数
        map.put("sort","[[${sort}]]");
        
        //发送数据
        List<[[${className}]]> list=[[${className2}]]Service.select(map);
        int total=[[${className2}]]Service.count(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(list);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response,result);
    }
}