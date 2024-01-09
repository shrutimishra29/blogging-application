import React from 'react';
import '../components/styles/footer.css'
const MyFooter = () => {
  return (
    <div>
      <section>
        <footer className="bg-body-tertiary" style={{ marginTop: '1%' }}>
          <div className="container p-4">
            <div className="row">
              <div className="col-lg-6 col-md-12 mb-4 mb-md-0">
                <h5 className="text-uppercase">Footer Content</h5>

                <p>
                   Welcome to Velvet Voices, where diverse stories come together.
            Explore a tapestry of experiences, thoughts, and ideas woven by our
            vibrant community. Join us in celebrating the beauty of
            self-expression and the power of storytelling.
                </p>
              </div>

           
              <div className="col-lg-3 col-md-6 mb-4 mb-md-0" style={{marginLeft: "25%"}}>
                <h5 className="text-uppercase mb-0">Links</h5>

                <ul className="list-unstyled">
                  <li>
                    <a href="#!" className="text-body">
                      Contact us
                    </a>
                  </li>
                  <li>
                    <a href="#!" className="text-body">
                      About
                    </a>
                  </li>
                  <li>
                    <a href="#!" className="text-body">
                      Social Media
                    </a>
                  </li>
                  <li>
                    <a href="#!" className="text-body">
                      Help
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>

          <div
            className="text-center p-3"
            style={{ backgroundColor: '#8d3b72' }}
          >
            © 2020 Copyright:
            <a className="text-body" href="https://mdbootstrap.com/">
              MDBootstrap.com
            </a>
          </div>
        </footer>
      </section>
    </div>
  );
};

export default MyFooter;
