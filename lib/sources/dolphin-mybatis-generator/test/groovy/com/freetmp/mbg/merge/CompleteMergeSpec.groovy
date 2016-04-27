package com.freetmp.mbg.merge

import spock.lang.Specification

/**
 * Created by pin on 2015/6/1.
 */
class CompleteMergeSpec extends Specification {

  def "test on all parts"() {
    expect:
    CompilationUnitMerger.merge(first, second).trim() == result.trim()
    where:
    first =
        """
/**
 * Copyright 2015-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Long id;

    private String loginName;

    private String name;

    private String password;

    private String salt;

    private String roles;

    private Date registerDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles == null ? null : roles.trim();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginName=").append(loginName);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", roles=").append(roles);
        sb.append(", registerDate=").append(registerDate);
        sb.append("]");
        return sb.toString();
    }
}
"""
    second =
        """
/**
 * Copyright 2015-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Long id;

    private String loginName;

    private String name;

    private String password;

    private String salt;

    private String roles;

    private Date registerDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles == null ? null : roles.trim();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginName=").append(loginName);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", roles=").append(roles);
        sb.append(", registerDate=").append(registerDate);
        sb.append("]");
        return sb.toString();
    }
}
"""
    result =
        """
/**
 * Copyright 2015-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private Long id;

    private String loginName;

    private String name;

    private String password;

    private String salt;

    private String roles;

    private Date registerDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles == null ? null : roles.trim();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new  StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginName=").append(loginName);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", roles=").append(roles);
        sb.append(", registerDate=").append(registerDate);
        sb.append("]");
        return sb.toString();
    }
}
"""
  }

  def "test on class with a few fields"(){
    expect:
    CompilationUnitMerger.merge(first, second).trim() == result.trim()
    where:
    first =
"""
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;

    private String loginName;

    private String name;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
"""
    second =
        """
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;

    private String loginName;

    private String name;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
"""
    result =
        """
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;

    private String loginName;

    private String name;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
"""
  }

  def "test on class with only one field"(){
    expect:
    CompilationUnitMerger.merge(first, second).trim() == result.trim()
    where:
    first =
        """
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
"""
    second =
        """
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
"""
    result =
        """
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
"""
  }

  def "test on class with only one field and getter"(){
    expect:
    CompilationUnitMerger.merge(first, second).trim() == result.trim()
    where:
    first =
        """
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }
}
"""
    second =
        """
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }
}
"""
    result =
        """
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }
}
"""
  }

  def "test on class with only one field and setter"(){
    expect:
    CompilationUnitMerger.merge(first, second).trim() == result.trim()
    where:
    first =
        """
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;

    public void setId(Long id) {
        this.id = id;
    }
}
"""
    second =
        """
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;

    public void setId(Long id) {
        this.id = id;
    }
}
"""
    result =
        """
package com.freetmp.xmbg.test.entity;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;

    private static final long serialVersionUID = 1L;

    public void setId(Long id) {
        this.id = id;
    }
}
"""
  }
}
