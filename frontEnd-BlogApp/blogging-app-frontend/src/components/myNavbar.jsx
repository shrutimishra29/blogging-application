import { Link, NavLink as ReactLink } from "react-router-dom";
import React, { useState } from 'react';
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  NavbarText,
} from 'reactstrap';

const MyNavbar = () => {
    
    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => setIsOpen(!isOpen);
    return (
        <div>
            <div>
      <Navbar color="dark" dark>
        <NavbarBrand href="/">reactstrap</NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="me-auto" navbar>
            <NavItem>
              <NavLink tag={ReactLink} to='/login'>Login</NavLink>
            </NavItem>
            <NavItem>
              <NavLink tag={ReactLink} to='/signup'>Signup</NavLink>
            </NavItem>
            <NavItem>
              <NavLink tag={ReactLink} to='/about'>About</NavLink>
            </NavItem>
          </Nav>
        </Collapse>
      </Navbar>
    </div>
        </div>
    );
}

export default MyNavbar;