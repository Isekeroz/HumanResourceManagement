package com.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.Address;
import com.rest.entity.City;
import com.rest.entity.Country;
import com.rest.entity.Department;
import com.rest.entity.District;
import com.rest.entity.Role;
import com.rest.entity.User;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="/api")
public class Controller {
	@Autowired
	private UserService userService;
	
	@Autowired 
	private DepartmentService departmentService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private DistrictService districtService;
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody(required=true) Map<String, String> obj ) {		

		
		String value = ""; 
				
		value = obj.get("user");
		
		User user = new User();
		
		JSONObject jsonObj = new JSONObject(value);
		
		user.setName((String) jsonObj.get("name"));
		user.setPassword((String) jsonObj.get("password"));
		user.setPhone((String) jsonObj.get("phone"));
		
		Department department = departmentService.getDepartmentById(Integer.parseInt((String)jsonObj.get("department")));
		User userToRemove = null;
		if(department.getUserList() != null && department.getUserList().size() != 0){
			for(User u : department.getUserList()){
				if(u.getRole().getId() == 3){
					userService.removeFromDepartment(u.getId());
					userToRemove = u;
				}
			}
			if(userToRemove != null){
				department.getUserList().remove(userToRemove);
			}
		}
		user.setDepartment(department);

		Role role = roleService.getRoleById(Integer.parseInt((String)jsonObj.get("role")));
		user.setRole(role);
		
		user.setAddressList(new ArrayList<Address>());
		
		JSONArray addressList = jsonObj.getJSONArray("addresses");
		for(Object al : addressList){
			JSONObject tmpObject = (JSONObject) al;
			
			int districtId = Integer.parseInt((String)tmpObject.get("district"));
			District district = districtService.getDistrictById(districtId);
			Address address = new Address();
			address.setDistrict(district);
			address.setCity(district.getCity());
			address.setCountry(district.getCity().getCountry());
			address.setName(tmpObject.getString("name"));
			address.setUser(user);
			
			user.getAddressList().add(address);
		}
		
		userService.save(user);		
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/createDepartment", method = RequestMethod.POST)
	public Response createDepartment(@RequestBody(required=true) Map<String, String> obj ) {		

		String departmentName = obj.get("name");
		
		
		Department department= new Department();
		department.setName(departmentName);
		
		departmentService.save(department);
		
		Response response = new Response();
		response.setStatus(0, "success");

		return response;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/listDepartment", method = RequestMethod.POST)
	public List<Department>  getDepartments(@RequestBody(required=true) Map<String, String> obj ) {		
		
		List<Department> departmentList = new ArrayList<Department>();
		departmentList = departmentService.getDepartments();

		return departmentList;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/listUsers", method = RequestMethod.POST)
	public List<User>  getUsers(@RequestBody(required=true) Map<String, String> obj ) {		
		
		List<User> userList = new ArrayList<User>();
		userList = userService.getUsers();

		return userList;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/listRole", method = RequestMethod.POST)
	public List<Role>  getRoles(@RequestBody(required=true) Map<String, String> obj ) {		
		
		List<Role> roleList = new ArrayList<Role>();
		roleList = roleService.getRoles();

		return roleList;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/createRole", method = RequestMethod.POST)
	public Response createRole(@RequestBody(required=true) Map<String, String> obj ) {		

		String roleName = obj.get("name");
		
		
		Role role= new Role();
		role.setName(roleName);
		
		roleService.save(role);
		
		Response response = new Response();
		response.setStatus(0, "success");

		return response;
	}
		
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/listCountries", method = RequestMethod.POST)
	public List<Country>  getCountries() {		
		
		List<Country> countryList = new ArrayList<Country>();
		countryList = countryService.getCountries();

		return countryList;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/listCities", method = RequestMethod.POST)
	public List<City>  getCities(@RequestBody(required=true) Map<String, String> obj ) {		
		
		int countryId = Integer.parseInt(obj.get("countryId"));
		
		List<City> cityList = new ArrayList<City>();
		cityList = cityService.getCityByCountryId(countryId);

		return cityList;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/listDistricts", method = RequestMethod.POST)
	public List<District>  getDistricts(@RequestBody(required=true) Map<String, String> obj ) {		
		
		int cityId = Integer.parseInt(obj.get("cityId"));
		
		List<District> districtList = new ArrayList<District>();
		districtList = districtService.getDistrictByCityId(cityId);

		return districtList;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/controlDepartmentManager", method = RequestMethod.POST)
	public ResponseEntity<Integer> controlDepartmentManager(@RequestBody(required=true) Map<String, String> obj ) {		
		
		int departmentId = Integer.parseInt(obj.get("departmentId"));
		int numberOfManagers = departmentService.controlManager(departmentId);
		
		return new ResponseEntity<Integer>(numberOfManagers, HttpStatus.OK);
	}
	
	
}
